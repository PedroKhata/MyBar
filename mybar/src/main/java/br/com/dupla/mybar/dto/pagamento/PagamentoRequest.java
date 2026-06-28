package br.com.dupla.mybar.dto.pagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PagamentoRequest(
        @NotNull(message = "O ID da conta é obrigatório.")
        Long contaId,

        @NotNull(message = "O código de quem está lançando o pagamento é obrigatório.")
        Integer quemLancouCodigo,

        @NotBlank(message = "A forma de pagamento (PIX, DEBITO, CREDITO, DINHEIRO) é obrigatória.")
        String forma,

        @NotNull(message = "O valor é obrigatório.")
        @Positive(message = "O valor do pagamento deve ser maior que zero.")
        BigDecimal valor
) {}