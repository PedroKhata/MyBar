package br.com.dupla.mybar.dto.itensconta;

import br.com.dupla.mybar.entity.ItensDaConta;
import java.time.LocalDate;
import java.time.LocalTime;

public record ItensDaContaResponse(
    Long id,
    Long contaId,
    Integer itemCardapioCodigo,
    String itemCardapioDescricao,
    Integer quantidade,
    Boolean ativo,
    LocalDate dataSolicitacao,
    LocalTime horaSolicitacao,
    LocalDate dataRecebimentoCozinha,
    LocalTime horaRecebimentoCozinha,
    LocalDate dataEntregaCozinha,
    LocalTime horaEntregaCozinha,
    LocalDate dataRecebimentoBar,
    LocalTime horaRecebimentoBar,
    LocalDate dataEntregaBar,
    LocalTime horaEntregaBar
) {
    public static ItensDaContaResponse fromEntity(ItensDaConta i) {
        return new ItensDaContaResponse(
            i.getId(),
            i.getConta().getId(),
            i.getItemCardapio().getCodigo(),
            i.getItemCardapio().getDescricao(),
            i.getQuantidade(),
            i.getAtivo(),
            i.getDataSolicitacao(),
            i.getHoraSolicitacao(),
            i.getDataRecebimentoCozinha(),
            i.getHoraRecebimentoCozinha(),
            i.getDataEntregaCozinha(),
            i.getHoraEntregaCozinha(),
            i.getDataRecebimentoBar(),
            i.getHoraRecebimentoBar(),
            i.getDataEntregaBar(),
            i.getHoraEntregaBar()
        );
    }
}