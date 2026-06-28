package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itens.ItensDaContaResponse;
import br.com.dupla.mybar.service.CozinhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cozinha")
public class CozinhaController {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @GetMapping("/solicitados")
    public ResponseEntity<List<ItensDaContaResponse>> listarSolicitados() {
        return ResponseEntity.ok(cozinhaService.listarSolicitados());
    }

    @GetMapping("/em-preparo")
    public ResponseEntity<List<ItensDaContaResponse>> listarEmPreparo() {
        return ResponseEntity.ok(cozinhaService.listarEmPreparo());
    }

    @PatchMapping("/{id}/receber")
    public ResponseEntity<ItensDaContaResponse> receberPedido(@PathVariable Long id) {
        return ResponseEntity.ok(cozinhaService.receberPedido(id));
    }

    @PatchMapping("/{id}/entregar")
    public ResponseEntity<ItensDaContaResponse> entregarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(cozinhaService.entregarPedido(id));
    }
}