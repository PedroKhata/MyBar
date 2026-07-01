package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itens.TipoItemRequest;
import br.com.dupla.mybar.dto.itens.TipoItemResponse;
import br.com.dupla.mybar.service.TipoItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<TipoItemResponse>> listar() {
        return ResponseEntity.ok(tipoItemService.listarAtivos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<TipoItemResponse> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(tipoItemService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<TipoItemResponse> criar(@Valid @RequestBody TipoItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoItemService.salvar(request));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<TipoItemResponse> atualizar(@PathVariable Integer codigo, @Valid @RequestBody TipoItemRequest request) {
        return ResponseEntity.ok(tipoItemService.atualizar(codigo, request));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable Integer codigo) {
        tipoItemService.excluirOuDesativar(codigo);
        return ResponseEntity.noContent().build();
    }
}