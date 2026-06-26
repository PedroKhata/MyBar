package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.configuracao.ConfiguracaoRequest;
import br.com.dupla.mybar.dto.configuracao.ConfiguracaoResponse;
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
    public ResponseEntity<ConfiguracaoResponse> salvar(@RequestBody ConfiguracaoRequest request) {
        return ResponseEntity.ok(configuracaoService.salvar(request));
    }

    @GetMapping("/hoje")
    public ResponseEntity<ConfiguracaoResponse> buscarHoje() {
        return configuracaoService.buscarHoje()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ConfiguracaoResponse> listarTodas() {
        return configuracaoService.listarTodas();
    }
}