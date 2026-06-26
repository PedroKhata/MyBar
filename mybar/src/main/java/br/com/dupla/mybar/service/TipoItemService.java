package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.tipoitem.TipoItemRequest;
import br.com.dupla.mybar.dto.tipoitem.TipoItemResponse;
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

    public List<TipoItemResponse> listarTodos() {
        return tipoItemRepository.findAll()
                .stream()
                .map(TipoItemResponse::fromEntity)
                .toList();
    }

    public TipoItemResponse buscarPorCodigo(Integer codigo) {
        TipoItem tipo = tipoItemRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado: " + codigo));
        return TipoItemResponse.fromEntity(tipo);
    }

    public TipoItemResponse salvar(TipoItemRequest request) {
        TipoItem tipo = new TipoItem();
        tipo.setDescricao(request.descricao());
        tipo.setGorjeta(request.gorjeta());
        tipo.setCozinha(request.cozinha());
        return TipoItemResponse.fromEntity(tipoItemRepository.save(tipo));
    }

    public TipoItemResponse atualizar(Integer codigo, TipoItemRequest request) {
        TipoItem tipo = tipoItemRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("TipoItem não encontrado: " + codigo));
        tipo.setDescricao(request.descricao());
        tipo.setGorjeta(request.gorjeta());
        tipo.setCozinha(request.cozinha());
        return TipoItemResponse.fromEntity(tipoItemRepository.save(tipo));
    }

    public void deletar(Integer codigo) {
        if (!tipoItemRepository.existsById(codigo)) {
            throw new RuntimeException("TipoItem não encontrado: " + codigo);
        }
        try {
            tipoItemRepository.deleteById(codigo);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir: este tipo possui itens vinculados.");
        }
    }
}