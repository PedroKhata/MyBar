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
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado."));

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

        // 1. Buscar a Configuração do dia em que a conta foi aberta para o valor do ingresso
        Configuracao config = configuracaoRepository.findTopByDataOrderByHoraDesc(conta.getDataAbertura())
                .orElseThrow(() -> new RegraNegocioException("Não há configuração do bar registrada para o dia desta conta."));

        // 2. Definir valor do ingresso baseado no sexo do cliente
        BigDecimal valorIngresso = BigDecimal.ZERO;
        String sexoCliente = conta.getCliente().getSexo();
        if ("Masculino".equalsIgnoreCase(sexoCliente)) {
            valorIngresso = config.getValorIngressoMasc();
        } else if ("Feminino".equalsIgnoreCase(sexoCliente)) {
            valorIngresso = config.getValorIngressoFemin();
        }

        // 3. Somar consumo e calcular gorjetas por tipo de item
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

        // 4. Somar Pagamentos
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

        conta.setStatus("FECHADA");
        contaRepository.save(conta);

        return new ContaFechamentoResponse(
                conta.getId(), conta.getNumero(), conta.getStatus(),
                totalConsumo, totalGorjeta, valorIngresso,
                totalAPagar, totalPago, troco
        );
    }
}