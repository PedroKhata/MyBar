package br.com.dupla.mybar.dto;

import br.com.dupla.mybar.entity.Cliente;

public record ClienteResponseDTO(Long id, String nome, String cpf, String celular, String sexo) {
    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getSexo());
    }

}
