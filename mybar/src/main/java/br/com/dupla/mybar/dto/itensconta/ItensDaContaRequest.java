package br.com.dupla.mybar.dto.itensconta;

public record ItensDaContaRequest(
    Long contaId,
    Integer itemCardapioCodigo,
    Integer quemLancouCodigo,
    Integer quantidade
) {}