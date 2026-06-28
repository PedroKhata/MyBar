package br.com.dupla.mybar.dto.conta;

import jakarta.validation.constraints.NotNull;

public record ContaAberturaRequest(
        @NotNull(message = "O ID do cliente é obrigatório para abrir uma conta.")
        Long clienteId
) {}