package br.com.dupla.mybar.dto.itens;

import br.com.dupla.mybar.entity.ItemCardapio;
import java.math.BigDecimal;

public record ItemCardapioResponse(Integer codigo, String descricao, BigDecimal valor, Integer tipoItemCodigo, String tipoItemDescricao) {
    public ItemCardapioResponse(ItemCardapio item) {
        this(item.getCodigo(), item.getDescricao(), item.getValor(), item.getTipo().getCodigo(), item.getTipo().getDescricao());
    }
}