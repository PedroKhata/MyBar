package br.com.dupla.mybar.service;

import br.com.dupla.mybar.entity.ItemCardapio;
import br.com.dupla.mybar.repository.ItemCardapioRepository;
import br.com.dupla.mybar.entity.TipoItem;
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

    public List<ItemCardapio> listarTodos() {
        return itemCardapioRepository.findAll();
    }

    public ItemCardapio buscarPorCodigo(Integer codigo) {
        return itemCardapioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("ItemCardapio não encontrado: " + codigo));
    }

    public ItemCardapio salvar(Integer tipoItemCodigo, ItemCardapio itemCardapio) {
        TipoItem tipo = tipoItemRepository.findById(tipoItemCodigo)
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado: " + tipoItemCodigo));
        itemCardapio.setTipo(tipo);
        return itemCardapioRepository.save(itemCardapio);
    }

    public void deletar(Integer codigo) {
        itemCardapioRepository.deleteById(codigo);
    }
}