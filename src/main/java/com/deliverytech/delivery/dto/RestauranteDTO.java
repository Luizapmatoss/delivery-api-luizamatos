package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.*;

public class RestauranteDTO {

    @NotBlank(message = "O nome do restaurante é obrigatório.")
    private String nome;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    @DecimalMin(value = "0.0", message = "A avaliação mínima é 0.")
    @DecimalMax(value = "5.0", message = "A avaliação máxima é 5.")
    private Double avaliacao;

    public RestauranteDTO() {}

    public RestauranteDTO(String nome, String categoria, Double avaliacao) {
        this.nome = nome;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
