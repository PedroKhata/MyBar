package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itemcardapio.ItemCardapioRequest;
import br.com.dupla.mybar.dto.itemcardapio.ItemCardapioResponse;
import br.com.dupla.mybar.service.ItemCardapioService;
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
    public List<ItemCardapioResponse> listarTodos() {
        return itemCardapioService.listarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ItemCardapioResponse> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(itemCardapioService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criar(@RequestBody ItemCardapioRequest request) {
        return ResponseEntity.ok(itemCardapioService.salvar(request));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ItemCardapioResponse> atualizar(@PathVariable Integer codigo, @RequestBody ItemCardapioRequest request) {
        return ResponseEntity.ok(itemCardapioService.atualizar(codigo, request));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
        itemCardapioService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}