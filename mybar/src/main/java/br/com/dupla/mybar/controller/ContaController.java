package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.conta.ContaAberturaRequest;
import br.com.dupla.mybar.dto.conta.ContaFechamentoResponse;
import br.com.dupla.mybar.dto.conta.ContaResponse;
import br.com.dupla.mybar.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaResponse> abrirConta(@Valid @RequestBody ContaAberturaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.abrirConta(request));
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<ContaResponse>> listarAbertas() {
        return ResponseEntity.ok(contaService.listarAbertas());
    }

    @PatchMapping("/{contaId}/fechar")
    public ResponseEntity<ContaFechamentoResponse> fecharConta(@PathVariable Long contaId) {
        return ResponseEntity.ok(contaService.fecharConta(contaId));
    }
}