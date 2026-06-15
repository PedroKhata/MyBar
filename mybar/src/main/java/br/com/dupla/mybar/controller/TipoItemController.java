package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.service.TipoItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipos-item")
public class TipoItemController {

    private final TipoItemService tipoItemService;

    public TipoItemController(TipoItemService tipoItemService) {
        this.tipoItemService = tipoItemService;
    }
}