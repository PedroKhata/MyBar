package br.com.dupla.mybar.dto.conta;

import java.math.BigDecimal;

public record ContaFechamentoResponse(
        Long contaId,
        Integer numeroConta,
        String status,
        BigDecimal totalConsumo,
        BigDecimal totalGorjeta,
        BigDecimal valorIngresso,
        BigDecimal totalAPagar,
        BigDecimal totalPago,
        BigDecimal troco
) {}