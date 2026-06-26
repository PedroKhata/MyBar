package br.com.dupla.mybar.dto.configuracao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import br.com.dupla.mybar.entity.Configuracao;

public record ConfiguracaoResponse(
    Integer id,
    BigDecimal valorIngressoMasc,
    BigDecimal valorIngressoFemin,
    String modoOperacao,
    LocalDate data,
    LocalTime hora
) {
    public static ConfiguracaoResponse fromEntity(Configuracao c) {
        return new ConfiguracaoResponse(
            c.getId(),
            c.getValorIngressoMasc(),
            c.getValorIngressoFemin(),
            c.getModoOperacao(),
            c.getData(),
            c.getHora()
        );
    }
}