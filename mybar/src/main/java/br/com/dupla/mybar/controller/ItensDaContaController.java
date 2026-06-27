package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.itensconta.ItensDaContaRequest;
import br.com.dupla.mybar.dto.itensconta.ItensDaContaResponse;
import br.com.dupla.mybar.service.ItensDaContaService;
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
    public ResponseEntity<ItensDaContaResponse> lancar(@RequestBody ItensDaContaRequest request) {
        return ResponseEntity.ok(itensDaContaService.lancar(request));
    }

    @GetMapping("/conta/{contaId}")
    public List<ItensDaContaResponse> listarPorConta(@PathVariable Long contaId) {
        return itensDaContaService.listarPorConta(contaId);
    }
}