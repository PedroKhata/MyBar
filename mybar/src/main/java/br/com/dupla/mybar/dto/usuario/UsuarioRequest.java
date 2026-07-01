package br.com.dupla.mybar.dto.usuario;

import br.com.dupla.mybar.entity.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequest(
        @NotNull(message = "O código numérico do utilizador é obrigatório.")
        Integer codigo,

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha,

        @NotNull(message = "O tipo de utilizador é obrigatório.")
        TipoUsuario tipo
) {}