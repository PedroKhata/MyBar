package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.entity.ItensDaConta;
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
    public List<ItensDaConta> listarProntos() {
        return entregaService.listarProntosParaRetirada();
    }

    @GetMapping("/em-entrega")
    public List<ItensDaConta> listarEmEntrega() {
        return entregaService.listarEmEntrega();
    }

    @PatchMapping("/{id}/receber")
    public ResponseEntity<ItensDaConta> receberDoBar(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.receberDoBar(id));
    }

    @PatchMapping("/{id}/entregar")
    public ResponseEntity<ItensDaConta> entregarAoGarcom(@PathVariable Long id) {
        return ResponseEntity.ok(entregaService.entregarAoGarcom(id));
    }
}