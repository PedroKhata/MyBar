package br.com.dupla.mybar.dto.conta;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ContaAberturaRequest(
        @NotBlank(message = "O CPF é obrigatório.")
        @CPF(message = "CPF em formato inválido.")
        String cpf,

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        String celular,

        @NotBlank(message = "O sexo é obrigatório.")
        String sexo
) {}