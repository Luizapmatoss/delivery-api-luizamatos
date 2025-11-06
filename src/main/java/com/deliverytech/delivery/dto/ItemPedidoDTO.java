package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemPedidoDTO {

    @NotNull
    private Long produtoId;

    @NotNull
    private BigDecimal preco;

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuantidade'");
    }
}
