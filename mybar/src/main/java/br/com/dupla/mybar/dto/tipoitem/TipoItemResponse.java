package br.com.dupla.mybar.dto.tipoitem;

import java.math.BigDecimal;
import br.com.dupla.mybar.entity.TipoItem;

public record TipoItemResponse(
    Integer codigo,
    String descricao,
    BigDecimal gorjeta,
    Boolean cozinha
) {
    public static TipoItemResponse fromEntity(TipoItem t) {
        return new TipoItemResponse(t.getCodigo(), t.getDescricao(), t.getGorjeta(), t.getCozinha());
    }
}