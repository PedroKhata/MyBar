package br.com.dupla.mybar.service;

import br.com.dupla.mybar.repository.TipoItemRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoItemService {

    private final TipoItemRepository tipoItemRepository;

    public TipoItemService(TipoItemRepository tipoItemRepository) {
        this.tipoItemRepository = tipoItemRepository;
    }
}