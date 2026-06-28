package br.com.dupla.mybar.dto.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoCancelamentoRequest(
        @NotNull(message = "O código do administrador é obrigatório.")
        Integer quemExcluiuCodigo,

        @NotBlank(message = "A senha do administrador é obrigatória para o estorno.")
        String senhaAdmin
) {}