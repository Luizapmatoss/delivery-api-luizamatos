package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.*;

/**
 * DTO responsável por transportar os dados do Restaurante
 * entre as camadas da aplicação.
 *
 * Possui validações importantes para garantir:
 * - Nome obrigatório
 * - Categoria obrigatória
 * - Avaliação entre 0 e 5
 */
public class RestauranteDTO {

    @NotBlank(message = "O nome do restaurante é obrigatório.")
    private String nome; 
    // Nome do restaurante. Campo obrigatório porque identifica o estabelecimento.

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria; 
    // Categoria do restaurante (ex: Japonesa, Lanches, Brasileira).

    @DecimalMin(value = "0.0", message = "A avaliação mínima é 0.")
    @DecimalMax(value = "5.0", message = "A avaliação máxima é 5.")
    private Double avaliacao;  
    // Avaliação do restaurante, variando de 0 a 5. Valor opcional, mas validado.

    // Construtor padrão necessário para frameworks como Jackson
    public RestauranteDTO() {}

    // Construtor completo para facilitar criação manual em testes ou serviços
    public RestauranteDTO(String nome, String categoria, Double avaliacao) {
        this.nome = nome;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
    }

    // Getters e Setters (padrão para DTOs)
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
