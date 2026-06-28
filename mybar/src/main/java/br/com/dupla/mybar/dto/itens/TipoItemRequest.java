package br.com.dupla.mybar.dto.itens;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record TipoItemRequest(
        @NotNull(message = "O código numérico é obrigatório.")
        Integer codigo,

        @NotBlank(message = "A descrição é obrigatória.")
        String descricao,

        @NotNull(message = "O percentual de gorjeta é obrigatório.")
        @PositiveOrZero(message = "A gorjeta não pode ser negativa.")
        BigDecimal gorjeta,

        @NotNull(message = "É obrigatório informar se o item vai para a cozinha.")
        Boolean cozinha
) {}