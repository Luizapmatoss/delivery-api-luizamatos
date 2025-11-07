package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemPedidoDTO {

    @NotNull(message = "O ID do produto é obrigatório.")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória.")
    private Integer quantidade;

    private BigDecimal preco;

    // Construtores
    public ItemPedidoDTO() {}

    public ItemPedidoDTO(Long produtoId, Integer quantidade, BigDecimal preco) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
