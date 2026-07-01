package br.com.dupla.mybar.dto.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelamentoRequest(
        @NotNull(message = "O código do administrador é obrigatório.")
        Integer codigoAdmin,

        @NotBlank(message = "A senha é obrigatória.")
        String senhaAdmin
) {}