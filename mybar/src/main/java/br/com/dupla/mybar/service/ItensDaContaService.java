package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.itens.ItensDaContaRequest;
import br.com.dupla.mybar.dto.itens.ItensDaContaResponse;
import br.com.dupla.mybar.entity.Conta;
import br.com.dupla.mybar.entity.ItemCardapio;
import br.com.dupla.mybar.entity.ItensDaConta;
import br.com.dupla.mybar.entity.Usuario;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.ContaRepository;
import br.com.dupla.mybar.repository.ItemCardapioRepository;
import br.com.dupla.mybar.repository.ItensDaContaRepository;
import br.com.dupla.mybar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItensDaContaService {

    private final ItensDaContaRepository itensDaContaRepository;
    private final ContaRepository contaRepository;
    private final ItemCardapioRepository itemCardapioRepository;
    private final UsuarioRepository usuarioRepository;

    public ItensDaContaService(
            ItensDaContaRepository itensDaContaRepository,
            ContaRepository contaRepository,
            ItemCardapioRepository itemCardapioRepository,
            UsuarioRepository usuarioRepository) {
        this.itensDaContaRepository = itensDaContaRepository;
        this.contaRepository = contaRepository;
        this.itemCardapioRepository = itemCardapioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ItensDaContaResponse lancar(ItensDaContaRequest request) {
        Conta conta = contaRepository.findById(request.contaId())
                .orElseThrow(() -> new RegraNegocioException("Conta não encontrada."));

        ItemCardapio item = itemCardapioRepository.findById(request.itemCardapioCodigo())
                .orElseThrow(() -> new RegraNegocioException("Item de cardápio não encontrado."));

        Usuario usuario = usuarioRepository.findById(request.quemLancouCodigo())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        if (!usuario.getSenha().equals(request.senhaGarcom())) {
            throw new RegraNegocioException("Senha incorreta. Lançamento não autorizado.");
        }

        ItensDaConta itemDaConta = new ItensDaConta();
        itemDaConta.setConta(conta);
        itemDaConta.setItemCardapio(item);
        itemDaConta.setQuemLancou(usuario);
        itemDaConta.setQuantidade(request.quantidade());
        itemDaConta.setAtivo(true);
        itemDaConta.setDataSolicitacao(LocalDate.now());
        itemDaConta.setHoraSolicitacao(LocalTime.now());

        return new ItensDaContaResponse(itensDaContaRepository.save(itemDaConta));
    }

    public List<ItensDaContaResponse> listarPorConta(Long contaId) {
        return itensDaContaRepository.findByContaIdAndAtivoTrue(contaId)
                .stream()
                .map(ItensDaContaResponse::new)
                .collect(Collectors.toList());
    }

    public void cancelarItem(Long idItem, br.com.dupla.mybar.dto.conta.CancelamentoRequest request) {
        Usuario admin = usuarioRepository.findById(request.codigoAdmin())
                .orElseThrow(() -> new RegraNegocioException("Administrador não encontrado."));

        if (admin.getTipo() != br.com.dupla.mybar.entity.TipoUsuario.ADMINISTRADOR) {
            throw new RegraNegocioException("Apenas administradores podem autorizar a exclusão de itens.");
        }

        if (!admin.getSenha().equals(request.senhaAdmin())) {
            throw new RegraNegocioException("Senha de administrador incorreta.");
        }

        ItensDaConta item = itensDaContaRepository.findById(idItem)
                .orElseThrow(() -> new RegraNegocioException("Item não encontrado na conta."));

        item.setAtivo(false);
        item.setQuemRemoveu(admin);

        itensDaContaRepository.save(item);
    }
}