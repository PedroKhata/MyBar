package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.entity.TipoItem;
import br.com.dupla.mybar.service.TipoItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-item")
public class TipoItemController {

    private final TipoItemService tipoItemService;

    public TipoItemController(TipoItemService tipoItemService) {
        this.tipoItemService = tipoItemService;
    }

    @GetMapping
    public List<TipoItem> listarTodos() {
        return tipoItemService.listarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TipoItem> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(tipoItemService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<TipoItem> criar(@RequestBody TipoItem tipoItem) {
        return ResponseEntity.ok(tipoItemService.salvar(tipoItem));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<TipoItem> atualizar(@PathVariable Integer codigo, @RequestBody TipoItem tipoItem) {
        tipoItem.setCodigo(codigo);
        return ResponseEntity.ok(tipoItemService.salvar(tipoItem));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
        tipoItemService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}