package br.com.dupla.mybar.dto.configuracao;

import java.math.BigDecimal;

public record ConfiguracaoRequest(
    BigDecimal valorIngressoMasc,
    BigDecimal valorIngressoFemin,
    String modoOperacao
) {}