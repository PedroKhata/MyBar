package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.usuario.UsuarioRequest;
import br.com.dupla.mybar.dto.usuario.UsuarioResponse;
import br.com.dupla.mybar.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@Valid @RequestBody UsuarioRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(usuarioService.listarAtivos());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<UsuarioResponse> buscarPorCodigo(@PathVariable Integer codigo) {
        return ResponseEntity.ok(usuarioService.buscarPorCodigo(codigo));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer codigo, @Valid @RequestBody UsuarioRequest dto) {
        return ResponseEntity.ok(usuarioService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable Integer codigo) {
        usuarioService.excluirOuDesativar(codigo);
        return ResponseEntity.noContent().build();
    }
}