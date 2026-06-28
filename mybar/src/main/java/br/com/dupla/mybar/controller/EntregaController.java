package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itens.ItensDaContaResponse;
import br.com.dupla.mybar.service.EntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping("/prontos")
    public ResponseEntity<List<ItensDaContaResponse>> listarProntos() {
        return ResponseEntity.ok(entregaService.listarProntosParaRetirada());
    }

    @GetMapping("/em-entrega")
    public ResponseEntity<List<ItensDaContaResponse>> listarEmEntrega() {
        return ResponseEntity.ok(entregaService.listarEmEntrega());
    }

    @PatchMapping("/{id}/receber")
    public ResponseEntity<ItensDaContaResponse> receberDoBar(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.receberDoBar(id));
    }

    @PatchMapping("/{id}/entregar")
    public ResponseEntity<ItensDaContaResponse> entregarAoGarcom(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.entregarAoGarcom(id));
    }
}