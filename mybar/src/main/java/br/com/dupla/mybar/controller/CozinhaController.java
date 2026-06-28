package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.entity.ItensDaConta;
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
    public List<ItensDaConta> listarSolicitados() {
        return cozinhaService.listarSolicitados();
    }

    @GetMapping("/em-preparo")
    public List<ItensDaConta> listarEmPreparo() {
        return cozinhaService.listarEmPreparo();
    }

    @PatchMapping("/{id}/receber")
    public ResponseEntity<ItensDaConta> receberPedido(@PathVariable Long id) {
        return ResponseEntity.ok(cozinhaService.receberPedido(id));
    }

    @PatchMapping("/{id}/entregar")
    public ResponseEntity<ItensDaConta> entregarPedido(@PathVariable Long id) {
        return ResponseEntity.ok(cozinhaService.entregarPedido(id));
    }
}