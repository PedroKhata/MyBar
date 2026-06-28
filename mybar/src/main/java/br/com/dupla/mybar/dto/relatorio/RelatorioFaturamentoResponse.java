package br.com.dupla.mybar.dto.relatorio;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RelatorioFaturamentoResponse(
        LocalDate dataInicio,
        LocalDate dataFim,
        Integer totalContasFechadas,
        BigDecimal faturamentoTotal
) {}