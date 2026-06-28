package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.pagamento.PagamentoCancelamentoRequest;
import br.com.dupla.mybar.dto.pagamento.PagamentoRequest;
import br.com.dupla.mybar.dto.pagamento.PagamentoResponse;
import br.com.dupla.mybar.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> lancar(@Valid @RequestBody PagamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.lancar(request));
    }

    @GetMapping("/conta/{contaId}")
    public ResponseEntity<List<PagamentoResponse>> listarPorConta(@PathVariable Long contaId) {
        return ResponseEntity.ok(pagamentoService.listarPorConta(contaId));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<PagamentoResponse> cancelar(@PathVariable Long id, @Valid @RequestBody PagamentoCancelamentoRequest request) {
        return ResponseEntity.ok(pagamentoService.cancelar(id, request));
    }
}