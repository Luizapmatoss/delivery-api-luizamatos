package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProdutoDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @Size(max = 255, message = "A descrição pode ter no máximo 255 caracteres.")
    private String descricao;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotNull(message = "O ID do restaurante é obrigatório.")
    private Long restauranteId;

    public ProdutoDTO() {}

    public ProdutoDTO(String nome, String descricao, String categoria, BigDecimal preco, Long restauranteId) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.restauranteId = restauranteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }
}
