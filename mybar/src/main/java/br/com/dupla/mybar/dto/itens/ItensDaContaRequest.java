package br.com.dupla.mybar.dto.itens;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItensDaContaRequest(
        @NotNull(message = "O ID da conta é obrigatório.")
        Long contaId,

        @NotNull(message = "O código do item do cardápio é obrigatório.")
        Integer itemCardapioCodigo,

        @NotNull(message = "O código do usuário que está lançando o pedido é obrigatório.")
        Integer quemLancouCodigo,

        @NotBlank(message = "A senha do garçom é obrigatória.")
        String senhaGarcom,

        @NotNull(message = "A quantidade é obrigatória.")
        @Positive(message = "A quantidade deve ser maior que zero.")
        Integer quantidade
) {}