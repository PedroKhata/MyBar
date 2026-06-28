package br.com.dupla.mybar.dto.conta;

import br.com.dupla.mybar.entity.Conta;
import java.time.LocalDate;
import java.time.LocalTime;

public record ContaResponse(Long id, Integer numero, String status, LocalDate dataAbertura, LocalTime horaAbertura, Long clienteId, String clienteNome) {
    public ContaResponse(Conta conta) {
        this(conta.getId(), conta.getNumero(), conta.getStatus(), conta.getDataAbertura(), conta.getHoraAbertura(), conta.getCliente().getId(), conta.getCliente().getNome());
    }
}