package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoDTO {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    private List<Long> produtosIds;

    public PedidoDTO() {}

    public PedidoDTO(Long clienteId, List<Long> produtosIds) {
        this.clienteId = clienteId;
        this.produtosIds = produtosIds;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getProdutosIds() {
        return produtosIds;
    }

    public void setProdutosIds(List<Long> produtosIds) {
        this.produtosIds = produtosIds;
    }

    public ItemPedidoDTO[] getItens() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItens'");
    }

    public Long getRestauranteId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRestauranteId'");
    }
}
