package com.deliverytech.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO utilizado para cadastro e atualização de clientes.")
public class ClienteDTO {

    @Schema(
            description = "Nome completo do cliente.",
            example = "Luiza Matos",
            minLength = 2,
            maxLength = 100
    )
    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @Schema(
            description = "E-mail do cliente. Deve ser um e-mail válido.",
            example = "luizamatos@gmail.com"
    )
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Informe um e-mail válido.")
    private String email;

    public ClienteDTO() {}

    public ClienteDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
