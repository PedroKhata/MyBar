package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.itemcardapio.ItemCardapioRequest;
import br.com.dupla.mybar.dto.itemcardapio.ItemCardapioResponse;
import br.com.dupla.mybar.entity.ItemCardapio;
import br.com.dupla.mybar.entity.TipoItem;
import br.com.dupla.mybar.repository.ItemCardapioRepository;
import br.com.dupla.mybar.repository.TipoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .map(ItemCardapioResponse::fromEntity)
                .toList();
    }

    public ItemCardapioResponse buscarPorCodigo(Integer codigo) {
        ItemCardapio item = itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("ItemCardapio não encontrado: " + codigo));
        return ItemCardapioResponse.fromEntity(item);
    }

    public ItemCardapioResponse salvar(ItemCardapioRequest request) {
        TipoItem tipo = tipoItemRepository.findById(request.tipoItemCodigo())
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado: " + request.tipoItemCodigo()));
        ItemCardapio item = new ItemCardapio();
        item.setDescricao(request.descricao());
        item.setValor(request.valor());
        item.setTipo(tipo);
        return ItemCardapioResponse.fromEntity(itemCardapioRepository.save(item));
    }

    public ItemCardapioResponse atualizar(Integer codigo, ItemCardapioRequest request) {
        ItemCardapio item = itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("ItemCardapio não encontrado: " + codigo));
        TipoItem tipo = tipoItemRepository.findById(request.tipoItemCodigo())
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado: " + request.tipoItemCodigo()));
        item.setDescricao(request.descricao());
        item.setValor(request.valor());
        item.setTipo(tipo);
        return ItemCardapioResponse.fromEntity(itemCardapioRepository.save(item));
    }

    public void deletar(Integer codigo) {
        if (!itemCardapioRepository.existsById(codigo)) {
            throw new RuntimeException("ItemCardapio não encontrado: " + codigo);
        }
        try {
            itemCardapioRepository.deleteById(codigo);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir: este item está vinculado a uma conta.");
        }
    }
}