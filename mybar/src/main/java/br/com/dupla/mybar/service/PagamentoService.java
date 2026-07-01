package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.pagamento.PagamentoCancelamentoRequest;
import br.com.dupla.mybar.dto.pagamento.PagamentoRequest;
import br.com.dupla.mybar.dto.pagamento.PagamentoResponse;
import br.com.dupla.mybar.entity.Conta;
import br.com.dupla.mybar.entity.Pagamento;
import br.com.dupla.mybar.entity.Usuario;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.ContaRepository;
import br.com.dupla.mybar.repository.PagamentoRepository;
import br.com.dupla.mybar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, ContaRepository contaRepository, UsuarioRepository usuarioRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.contaRepository = contaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public PagamentoResponse lancar(PagamentoRequest request) {
        Conta conta = contaRepository.findById(request.contaId())
                .orElseThrow(() -> new RegraNegocioException("Conta não encontrada."));

        // Se a conta já estiver fechada, não deve aceitar mais pagamentos
        if ("FECHADA".equalsIgnoreCase(conta.getStatus())) {
            throw new RegraNegocioException("Esta conta já está fechada e não aceita novos pagamentos.");
        }

        Usuario quemLancou = usuarioRepository.findById(request.quemLancouCodigo())
                .orElseThrow(() -> new RegraNegocioException("Usuário lançador não encontrado."));

        if (!quemLancou.getSenha().equals(request.senhaCaixa())) {
            throw new RegraNegocioException("Senha incorreta. Pagamento não autorizado.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setConta(conta);
        pagamento.setQuemLancouPg(quemLancou);
        pagamento.setForma(request.forma());
        pagamento.setValor(request.valor());
        pagamento.setAtivo(true);

        return new PagamentoResponse(pagamentoRepository.save(pagamento));
    }

    public List<PagamentoResponse> listarPorConta(Long contaId) {
        return pagamentoRepository.findByContaIdAndAtivoTrue(contaId)
                .stream()
                .map(PagamentoResponse::new)
                .collect(Collectors.toList());
    }

    public PagamentoResponse cancelar(Long id, PagamentoCancelamentoRequest request) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Pagamento não encontrado."));

        if (!pagamento.getAtivo()) {
            throw new RegraNegocioException("Este pagamento já foi cancelado anteriormente.");
        }

        Usuario admin = usuarioRepository.findById(request.quemExcluiuCodigo())
                .orElseThrow(() -> new RegraNegocioException("Usuário administrador não encontrado."));

        // Validação de segurança dupla: Checa o cargo e a senha (case sensitive)
        if (!"ADMIN".equalsIgnoreCase(admin.getTipo().name())) {
            throw new RegraNegocioException("Apenas usuários do tipo ADMIN podem cancelar pagamentos.");
        }

        if (!admin.getSenha().equals(request.senhaAdmin())) {
            throw new RegraNegocioException("Senha de administrador incorreta. Estorno negado.");
        }

        pagamento.setAtivo(false);
        pagamento.setQuemExcluiuPg(admin);

        return new PagamentoResponse(pagamentoRepository.save(pagamento));
    }
}