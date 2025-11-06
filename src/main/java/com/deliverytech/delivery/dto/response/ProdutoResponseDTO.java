package com.deliverytech.delivery.dto.response;

import java.math.BigDecimal;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private BigDecimal preco;
    private boolean disponivel;
    private Long restauranteId;

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(Long id, String nome, String descricao, String categoria,
                              BigDecimal preco, boolean disponivel, Long restauranteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.disponivel = disponivel;
        this.restauranteId = restauranteId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public Long getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Long restauranteId) { this.restauranteId = restauranteId; }
}
