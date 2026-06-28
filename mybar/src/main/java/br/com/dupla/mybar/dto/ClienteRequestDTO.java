package br.com.dupla.mybar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDTO (

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O CPF e obrigatorio.")
        @CPF(message = "CPF em formato invalido.")
        String cpf,

        @Size(max = 20, message = "O telefone celular deve ter no maximo 2000 caracteres.")
        String celular,

        @Pattern(regexp = "Masculino|Feminino", message = "O sexo deve ser 'Masculino' ou 'Feminino'.")
        String sexo

) {}
