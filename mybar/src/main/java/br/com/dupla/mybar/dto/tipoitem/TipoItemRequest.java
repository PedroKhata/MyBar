package br.com.dupla.mybar.dto.tipoitem;

import java.math.BigDecimal;

public record TipoItemRequest(
    String descricao,
    BigDecimal gorjeta,
    Boolean cozinha
) {}