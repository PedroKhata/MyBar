package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.itensconta.ItensDaContaRequest;
import br.com.dupla.mybar.dto.itensconta.ItensDaContaResponse;
import br.com.dupla.mybar.entity.Conta;
import br.com.dupla.mybar.entity.ItemCardapio;
import br.com.dupla.mybar.entity.ItensDaConta;
import br.com.dupla.mybar.entity.Usuario;
import br.com.dupla.mybar.repository.ContaRepository;
import br.com.dupla.mybar.repository.ItemCardapioRepository;
import br.com.dupla.mybar.repository.ItensDaContaRepository;
import br.com.dupla.mybar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Conta não encontrada: " + request.contaId()));
        ItemCardapio item = itemCardapioRepository.findById(request.itemCardapioCodigo())
                .orElseThrow(() -> new RuntimeException("Item não encontrado: " + request.itemCardapioCodigo()));
        Usuario usuario = usuarioRepository.findById(request.quemLancouCodigo())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + request.quemLancouCodigo()));

        ItensDaConta itemDaConta = new ItensDaConta();
        itemDaConta.setConta(conta);
        itemDaConta.setItemCardapio(item);
        itemDaConta.setQuemLancou(usuario);
        itemDaConta.setQuantidade(request.quantidade());
        itemDaConta.setAtivo(true);
        itemDaConta.setDataSolicitacao(LocalDate.now());
        itemDaConta.setHoraSolicitacao(LocalTime.now());

        return ItensDaContaResponse.fromEntity(itensDaContaRepository.save(itemDaConta));
    }

    public List<ItensDaContaResponse> listarPorConta(Long contaId) {
        return itensDaContaRepository.findAll()
                .stream()
                .filter(i -> i.getConta().getId().equals(contaId) && i.getAtivo())
                .map(ItensDaContaResponse::fromEntity)
                .toList();
    }
}