package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.entity.Configuracao;
import br.com.dupla.mybar.service.ConfiguracaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuracoes")
public class ConfiguracaoController {

    private final ConfiguracaoService configuracaoService;

    public ConfiguracaoController(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    @PostMapping
    public ResponseEntity<Configuracao> salvar(@RequestBody Configuracao configuracao) {
        return ResponseEntity.ok(configuracaoService.salvar(configuracao));
    }

    @GetMapping("/hoje")
    public ResponseEntity<Configuracao> buscarHoje() {
        return configuracaoService.buscarHoje()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Configuracao> listarTodas() {
        return configuracaoService.listarTodas();
    }
}