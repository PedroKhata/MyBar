package br.com.dupla.mybar.dto.itens;

import br.com.dupla.mybar.entity.TipoItem;
import java.math.BigDecimal;

public record TipoItemResponse(Integer codigo, String descricao, BigDecimal gorjeta, Boolean cozinha) {
    public TipoItemResponse(TipoItem tipo) {
        this(tipo.getCodigo(), tipo.getDescricao(), tipo.getGorjeta(), tipo.getCozinha());
    }
}