package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.relatorio.RelatorioFaturamentoResponse;
import br.com.dupla.mybar.service.RelatorioService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/faturamento")
    public ResponseEntity<RelatorioFaturamentoResponse> relatorioFaturamento(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        return ResponseEntity.ok(relatorioService.gerarRelatorioFaturamento(inicio, fim));
    }
}