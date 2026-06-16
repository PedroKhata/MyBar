package br.com.dupla.mybar.service;

import br.com.dupla.mybar.entity.TipoItem;
import br.com.dupla.mybar.repository.TipoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoItemService {

    private final TipoItemRepository tipoItemRepository;

    public TipoItemService(TipoItemRepository tipoItemRepository) {
        this.tipoItemRepository = tipoItemRepository;
    }

    public List<TipoItem> listarTodos() {
        return tipoItemRepository.findAll();
    }

    public TipoItem buscarPorCodigo(Integer codigo) {
        return tipoItemRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado: " + codigo));
    }

    public TipoItem salvar(TipoItem tipoItem) {
        return tipoItemRepository.save(tipoItem);
    }

    public void deletar(Integer codigo) {
        tipoItemRepository.deleteById(codigo);
    }
}