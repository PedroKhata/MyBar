package br.com.dupla.mybar.dto.usuario;

import br.com.dupla.mybar.entity.TipoUsuario;
import br.com.dupla.mybar.entity.Usuario;

public record UsuarioResponse(Integer codigo, String nome, String email, TipoUsuario tipo, Boolean ativo) {
    public UsuarioResponse(Usuario usuario) {
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getTipo(), usuario.getAtivo());
    }
}