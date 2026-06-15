package br.com.dupla.mybar.service;

import br.com.dupla.mybar.repository.ItemCardapioRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;

    public ItemCardapioService(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }
}