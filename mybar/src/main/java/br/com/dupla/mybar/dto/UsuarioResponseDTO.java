package br.com.dupla.mybar.dto;

import br.com.dupla.mybar.entity.TipoUsuario;
import br.com.dupla.mybar.entity.Usuario;

public record UsuarioResponseDTO(Integer codigo, String nome, String email, TipoUsuario tipo, Boolean ativo) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getTipo(), usuario.getAtivo());
    }
}