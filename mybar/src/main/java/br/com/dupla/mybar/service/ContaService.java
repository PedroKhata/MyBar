package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.conta.ContaAberturaRequest;
import br.com.dupla.mybar.dto.conta.ContaFechamentoResponse;
import br.com.dupla.mybar.dto.conta.ContaResponse;
import br.com.dupla.mybar.entity.*;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;
    private final ConfiguracaoRepository configuracaoRepository;
    private final ItensDaContaRepository itensDaContaRepository;
    private final PagamentoRepository pagamentoRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository,
                        ConfiguracaoRepository configuracaoRepository, ItensDaContaRepository itensDaContaRepository,
                        PagamentoRepository pagamentoRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.configuracaoRepository = configuracaoRepository;
        this.itensDaContaRepository = itensDaContaRepository;
        this.pagamentoRepository = pagamentoRepository;
    }

    public ContaResponse abrirConta(ContaAberturaRequest request) {
        Cliente cliente = clienteRepository.findByCpf(request.cpf()).orElseGet(() -> {
            Cliente novoCliente = new Cliente();
            novoCliente.setCpf(request.cpf());
            novoCliente.setNome(request.nome());
            novoCliente.setCelular(request.celular());
            novoCliente.setSexo(request.sexo());
            return clienteRepository.save(novoCliente);
        });

        Integer proximoNumero = contaRepository.findMaxNumero() + 1;

        Conta conta = new Conta();
        conta.setNumero(proximoNumero);
        conta.setStatus("ABERTA");
        conta.setDataAbertura(LocalDate.now());
        conta.setHoraAbertura(LocalTime.now());
        conta.setCliente(cliente);

        return new ContaResponse(contaRepository.save(conta));
    }

    public List<ContaResponse> listarAbertas() {
        return contaRepository.findByStatus("ABERTA").stream()
                .map(ContaResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContaFechamentoResponse fecharConta(Long contaId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RegraNegocioException("Conta não encontrada."));

        if ("FECHADA".equalsIgnoreCase(conta.getStatus())) {
            throw new RegraNegocioException("Esta conta já está fechada.");
        }

        Configuracao config = configuracaoRepository.findTopByDataOrderByHoraDesc(conta.getDataAbertura())
                .orElseThrow(() -> new RegraNegocioException("Não há configuração do bar registrada para o dia desta conta."));

        BigDecimal valorIngresso = BigDecimal.ZERO;
        String sexoCliente = conta.getCliente().getSexo();
        if ("Masculino".equalsIgnoreCase(sexoCliente)) {
            valorIngresso = config.getValorIngressoMasc();
        } else if ("Feminino".equalsIgnoreCase(sexoCliente)) {
            valorIngresso = config.getValorIngressoFemin();
        }

        BigDecimal totalConsumo = BigDecimal.ZERO;
        BigDecimal totalGorjeta = BigDecimal.ZERO;
        List<ItensDaConta> itens = itensDaContaRepository.findByContaIdAndAtivoTrue(contaId);

        for (ItensDaConta item : itens) {
            BigDecimal qtd = BigDecimal.valueOf(item.getQuantidade());
            BigDecimal valorUnitario = item.getItemCardapio().getValor();
            BigDecimal subtotalItem = valorUnitario.multiply(qtd);

            BigDecimal percentualGorjeta = item.getItemCardapio().getTipo().getGorjeta();
            BigDecimal valorGorjeta = subtotalItem.multiply(percentualGorjeta).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            totalConsumo = totalConsumo.add(subtotalItem);
            totalGorjeta = totalGorjeta.add(valorGorjeta);
        }

        List<Pagamento> pagamentos = pagamentoRepository.findByContaIdAndAtivoTrue(contaId);
        BigDecimal totalPago = pagamentos.stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalAPagar = totalConsumo.add(totalGorjeta).add(valorIngresso);

        if (totalPago.compareTo(totalAPagar) < 0) {
            BigDecimal falta = totalAPagar.subtract(totalPago);
            throw new RegraNegocioException("Saldo insuficiente. Faltam R$ " + falta + " para fechar a conta.");
        }

        BigDecimal troco = totalPago.subtract(totalAPagar);

        if (troco.compareTo(BigDecimal.ZERO) > 0) {
            Pagamento pagamentoTroco = new Pagamento();
            pagamentoTroco.setConta(conta);
            pagamentoTroco.setForma("DINHEIRO");
            pagamentoTroco.setValor(troco.negate());

            // O sistema procura o funcionário que registrou o último pagamento para lhe atribuir o troco
            if (!pagamentos.isEmpty()) {
                Usuario caixa = pagamentos.get(pagamentos.size() - 1).getQuemLancouPg();
                pagamentoTroco.setQuemLancouPg(caixa);
            }

            pagamentoTroco.setAtivo(true);
            pagamentoRepository.save(pagamentoTroco);
        }

        conta.setStatus("FECHADA");
        contaRepository.save(conta);

        return new ContaFechamentoResponse(
                conta.getId(), conta.getNumero(), conta.getStatus(),
                totalConsumo, totalGorjeta, valorIngresso,
                totalAPagar, totalPago, troco
        );
    }
}