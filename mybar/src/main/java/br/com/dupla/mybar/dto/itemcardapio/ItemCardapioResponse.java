package br.com.dupla.mybar.dto.itemcardapio;

import java.math.BigDecimal;
import br.com.dupla.mybar.entity.ItemCardapio;

public record ItemCardapioResponse(
    Integer codigo,
    String descricao,
    BigDecimal valor,
    Integer tipoItemCodigo,
    String tipoItemDescricao
) {
    public static ItemCardapioResponse fromEntity(ItemCardapio i) {
        return new ItemCardapioResponse(
            i.getCodigo(),
            i.getDescricao(),
            i.getValor(),
            i.getTipo().getCodigo(),
            i.getTipo().getDescricao()
        );
    }
}