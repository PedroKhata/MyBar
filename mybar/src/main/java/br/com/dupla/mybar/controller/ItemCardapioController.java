package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.entity.ItemCardapio;
import br.com.dupla.mybar.service.ItemCardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/itens-cardapio")
public class ItemCardapioController {

    private final ItemCardapioService itemCardapioService;

    public ItemCardapioController(ItemCardapioService itemCardapioService) {
        this.itemCardapioService = itemCardapioService;
    }

    @GetMapping
    public List<ItemCardapio> listarTodos() {
        return itemCardapioService.listarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ItemCardapio> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(itemCardapioService.buscarPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<ItemCardapio> criar(@RequestBody Map<String, Object> body) {
        ItemCardapio item = new ItemCardapio();
        item.setDescricao((String) body.get("descricao"));
        item.setValor(new java.math.BigDecimal(body.get("valor").toString()));
        Integer tipoItemCodigo = (Integer) body.get("tipoItemCodigo");
        return ResponseEntity.ok(itemCardapioService.salvar(tipoItemCodigo, item));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<ItemCardapio> atualizar(@PathVariable Integer codigo, @RequestBody Map<String, Object> body) {
        ItemCardapio item = itemCardapioService.buscarPorCodigo(codigo);
        item.setDescricao((String) body.get("descricao"));
        item.setValor(new java.math.BigDecimal(body.get("valor").toString()));
        Integer tipoItemCodigo = (Integer) body.get("tipoItemCodigo");
        return ResponseEntity.ok(itemCardapioService.salvar(tipoItemCodigo, item));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deletar(@PathVariable Integer codigo) {
        itemCardapioService.deletar(codigo);
        return ResponseEntity.noContent().build();
    }
}