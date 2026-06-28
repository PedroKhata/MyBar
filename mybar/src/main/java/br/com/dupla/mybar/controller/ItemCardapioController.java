package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itens.ItemCardapioRequest;
import br.com.dupla.mybar.dto.itens.ItemCardapioResponse;
import br.com.dupla.mybar.service.ItemCardapioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }

    @GetMapping
    public ResponseEntity<List<ItemCardapioResponse>> listarTodos() {
        return ResponseEntity.ok(itemCardapioService.listarTodos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ItemCardapioResponse> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(itemCardapioService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criar(@Valid @RequestBody ItemCardapioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCardapioService.salvar(request));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ItemCardapioResponse> atualizar(@PathVariable Integer codigo, @Valid @RequestBody ItemCardapioRequest request) {
        return ResponseEntity.ok(itemCardapioService.atualizar(codigo, request));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
        itemCardapioService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}