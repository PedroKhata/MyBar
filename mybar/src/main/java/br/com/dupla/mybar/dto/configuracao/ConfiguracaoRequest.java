package br.com.dupla.mybar.dto.configuracao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record ConfiguracaoRequest(
        @NotNull(message = "O valor do ingresso masculino é obrigatório.")
        @PositiveOrZero(message = "O ingresso masculino não pode ser negativo.")
        BigDecimal valorIngressoMasc,

        @NotNull(message = "O valor do ingresso feminino é obrigatório.")
        @PositiveOrZero(message = "O ingresso feminino não pode ser negativo.")
        BigDecimal valorIngressoFemin,

        @NotBlank(message = "O modo de operação é obrigatório.")
        String modoOperacao
) {}