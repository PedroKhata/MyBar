package br.com.dupla.mybar.dto.cliente;

import br.com.dupla.mybar.entity.Cliente;

public record ClienteResponse(Long id, String nome, String cpf, String celular, String sexo) {
    public ClienteResponse(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getSexo());
    }
}