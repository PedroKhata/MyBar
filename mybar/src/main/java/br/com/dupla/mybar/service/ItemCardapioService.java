package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.itens.ItemCardapioRequest;
import br.com.dupla.mybar.dto.itens.ItemCardapioResponse;
import br.com.dupla.mybar.entity.ItemCardapio;
import br.com.dupla.mybar.entity.TipoItem;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.ItemCardapioRepository;
import br.com.dupla.mybar.repository.TipoItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;
    private final TipoItemRepository tipoItemRepository;

    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository, TipoItemRepository tipoItemRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
        this.tipoItemRepository = tipoItemRepository;
    }

    public List<ItemCardapioResponse> listarTodos() {
        return itemCardapioRepository.findAll()
                .stream()
                .map(ItemCardapioResponse::new)
                .collect(Collectors.toList());
    }

    public ItemCardapioResponse buscarPorCodigo(Integer codigo) {
        ItemCardapio item = itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Item de Cardápio não encontrado: " + codigo));
        return new ItemCardapioResponse(item);
    }

    public ItemCardapioResponse salvar(ItemCardapioRequest request) {
        if (itemCardapioRepository.existsById(request.codigo())) {
            throw new RegraNegocioException("Já existe um Item com este código.");
        }

        TipoItem tipo = tipoItemRepository.findById(request.tipoItemCodigo())
                .orElseThrow(() -> new RegraNegocioException("Tipo de Item não encontrado: " + request.tipoItemCodigo()));

        ItemCardapio item = new ItemCardapio();
        item.setCodigo(request.codigo()); // Correção: setando a PK manual
        item.setDescricao(request.descricao());
        item.setValor(request.valor());
        item.setTipo(tipo);
        tipo.setAtivo(true);

        return new ItemCardapioResponse(itemCardapioRepository.save(item));
    }

    public ItemCardapioResponse atualizar(Integer codigo, ItemCardapioRequest request) {
        ItemCardapio item = itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Item de Cardápio não encontrado: " + codigo));

        TipoItem tipo = tipoItemRepository.findById(request.tipoItemCodigo())
                .orElseThrow(() -> new RegraNegocioException("Tipo de Item não encontrado: " + request.tipoItemCodigo()));

        item.setDescricao(request.descricao());
        item.setValor(request.valor());
        item.setTipo(tipo);

        return new ItemCardapioResponse(itemCardapioRepository.save(item));
    }

    public void deletar(Integer codigo) {
        ItemCardapio item = itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Item de Cardápio não encontrado: " + codigo));
        try {
            itemCardapioRepository.delete(item);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Não é possível excluir: este item está vinculado a uma conta.");
        }
    }

    public List<ItemCardapioResponse> listarAtivos() {
        return itemCardapioRepository.findByAtivoTrue().stream()
                .map(ItemCardapioResponse::new)
                .toList();
    }

    public void excluirOuDesativar(Integer codigo) {
        ItemCardapio itemCardapio = itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Item do cardápio não encontrado."));

        try {
            itemCardapioRepository.delete(itemCardapio);
            itemCardapioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            itemCardapio.setAtivo(false);
            itemCardapioRepository.save(itemCardapio);
        }
    }
}