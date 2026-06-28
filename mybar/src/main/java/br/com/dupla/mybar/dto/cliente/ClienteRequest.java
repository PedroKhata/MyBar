package br.com.dupla.mybar.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest (
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O CPF é obrigatório.")
        @CPF(message = "CPF em formato inválido.")
        String cpf,

        @Size(max = 20, message = "O telefone celular deve ter no máximo 20 caracteres.")
        String celular,

        @Pattern(regexp = "Masculino|Feminino", message = "O sexo deve ser 'Masculino' ou 'Feminino'.")
        String sexo
) {}