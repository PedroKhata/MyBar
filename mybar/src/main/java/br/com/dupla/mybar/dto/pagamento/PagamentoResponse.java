package br.com.dupla.mybar.dto.pagamento;

import br.com.dupla.mybar.entity.Pagamento;
import java.math.BigDecimal;

public record PagamentoResponse(Long id, Long contaId, String forma, BigDecimal valor, Boolean ativo, String nomeLancador) {
    public PagamentoResponse(Pagamento p) {
        this(p.getId(), p.getConta().getId(), p.getForma(), p.getValor(), p.getAtivo(), p.getQuemLancouPg().getNome());
    }
}