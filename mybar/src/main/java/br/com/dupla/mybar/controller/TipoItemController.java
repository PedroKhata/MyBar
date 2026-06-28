package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.tipoitem.TipoItemRequest;
import br.com.dupla.mybar.dto.tipoitem.TipoItemResponse;
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
    public List<TipoItemResponse> listarTodos() {
        return tipoItemService.listarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TipoItemResponse> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(tipoItemService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<TipoItemResponse> criar(@RequestBody TipoItemRequest request) {
        return ResponseEntity.ok(tipoItemService.salvar(request));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<TipoItemResponse> atualizar(@PathVariable Integer codigo, @RequestBody TipoItemRequest request) {
        return ResponseEntity.ok(tipoItemService.atualizar(codigo, request));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
        tipoItemService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}