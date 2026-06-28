package br.com.dupla.mybar.dto.itens;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemCardapioRequest(
        @NotNull(message = "O código do item é obrigatório.")
        Integer codigo,

        @NotBlank(message = "A descrição do item é obrigatória.")
        String descricao,

        @NotNull(message = "O valor é obrigatório.")
        @Positive(message = "O valor deve ser maior que zero.")
        BigDecimal valor,

        @NotNull(message = "O código da categoria (Tipo de Item) é obrigatório.")
        Integer tipoItemCodigo
) {}