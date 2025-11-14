package com.deliverytech.delivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "DTO que representa um item dentro de um pedido.")
public class ItemPedidoDTO {

    @Schema(
            description = "ID do produto selecionado para o pedido.",
            example = "42",
            required = true
    )
    @NotNull(message = "O ID do produto é obrigatório.")
    private Long produtoId;

    @Schema(
            description = "Quantidade do produto desejada.",
            example = "3",
            minimum = "1",
            required = true
    )
    @NotNull(message = "A quantidade é obrigatória.")
    private Integer quantidade;

    @Schema(
            description = "Preço unitário do produto no momento do pedido.",
            example = "19.90"
    )
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
