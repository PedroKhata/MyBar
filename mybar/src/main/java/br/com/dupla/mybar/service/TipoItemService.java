package br.com.dupla.mybar.service;

import br.com.dupla.mybar.dto.itens.TipoItemRequest;
import br.com.dupla.mybar.dto.itens.TipoItemResponse;
import br.com.dupla.mybar.entity.TipoItem;
import br.com.dupla.mybar.exception.RegraNegocioException;
import br.com.dupla.mybar.repository.TipoItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoItemService {

    private final TipoItemRepository tipoItemRepository;

    public TipoItemService(TipoItemRepository tipoItemRepository) {
        this.tipoItemRepository = tipoItemRepository;
    }

    public List<TipoItemResponse> listarTodos() {
        return tipoItemRepository.findAll()
                .stream()
                .map(TipoItemResponse::new)
                .collect(Collectors.toList());
    }

    public TipoItemResponse buscarPorCodigo(Integer codigo) {
        TipoItem tipo = tipoItemRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Tipo de Item não encontrado: " + codigo));
        return new TipoItemResponse(tipo);
    }

    public TipoItemResponse salvar(TipoItemRequest request) {
        if (tipoItemRepository.existsById(request.codigo())) {
            throw new RegraNegocioException("Já existe um Tipo de Item com este código.");
        }

        TipoItem tipo = new TipoItem();
        tipo.setCodigo(request.codigo()); // Correção crucial: setando a PK manual
        tipo.setDescricao(request.descricao());
        tipo.setGorjeta(request.gorjeta());
        tipo.setCozinha(request.cozinha());

        return new TipoItemResponse(tipoItemRepository.save(tipo));
    }

    public TipoItemResponse atualizar(Integer codigo, TipoItemRequest request) {
        TipoItem tipo = tipoItemRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Tipo de Item não encontrado: " + codigo));

        tipo.setDescricao(request.descricao());
        tipo.setGorjeta(request.gorjeta());
        tipo.setCozinha(request.cozinha());

        return new TipoItemResponse(tipoItemRepository.save(tipo));
    }

    public void deletar(Integer codigo) {
        TipoItem tipo = tipoItemRepository.findById(codigo)
                .orElseThrow(() -> new RegraNegocioException("Tipo de Item não encontrado: " + codigo));
        try {
            tipoItemRepository.delete(tipo);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Não é possível excluir: este tipo possui itens vinculados.");
        }
    }
}