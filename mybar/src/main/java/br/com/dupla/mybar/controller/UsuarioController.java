package br.com.dupla.mybar.controller;

import br.com.dupla.mybar.dto.UsuarioRequestDTO;
import br.com.dupla.mybar.dto.UsuarioResponseDTO;
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
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarAtivos());
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable Integer codigo) {
        usuarioService.excluirOuDesativar(codigo);
        return ResponseEntity.noContent().build();
    }
}