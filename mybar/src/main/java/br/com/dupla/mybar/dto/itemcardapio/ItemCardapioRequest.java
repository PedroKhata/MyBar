package br.com.dupla.mybar.dto.itemcardapio;

import java.math.BigDecimal;

public record ItemCardapioRequest(
    String descricao,
    BigDecimal valor,
    Integer tipoItemCodigo
) {}