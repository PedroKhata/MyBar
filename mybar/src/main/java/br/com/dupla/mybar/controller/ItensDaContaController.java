package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itens.ItensDaContaRequest;
import br.com.dupla.mybar.dto.itens.ItensDaContaResponse;
import br.com.dupla.mybar.service.ItensDaContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-da-conta")
public class ItensDaContaController {

    private final ItensDaContaService itensDaContaService;

    public ItensDaContaController(ItensDaContaService itensDaContaService) {
        this.itensDaContaService = itensDaContaService;
    }

    @PostMapping
    public ResponseEntity<ItensDaContaResponse> lancar(@Valid @RequestBody ItensDaContaRequest request) {
        return ResponseEntity.ok(itensDaContaService.lancar(request));
    }

    @GetMapping("/conta/{contaId}")
    public List<ItensDaContaResponse> listarPorConta(@PathVariable Long contaId) {
        return itensDaContaService.listarPorConta(contaId);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarItem(@PathVariable Long id, @RequestBody br.com.dupla.mybar.dto.conta.CancelamentoRequest request) {
        itensDaContaService.cancelarItem(id, request);
        return ResponseEntity.noContent().build();
    }
}