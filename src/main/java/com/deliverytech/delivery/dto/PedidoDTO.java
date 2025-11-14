package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @NotNull(message = "O ID do restaurante é obrigatório.")
    private Long restauranteId;

    @NotEmpty(message = "A lista de itens não pode estar vazia.")
    private List<ItemPedidoDTO> itens;

    public PedidoDTO() {}

    public PedidoDTO(Long clienteId, Long restauranteId, List<ItemPedidoDTO> itens) {
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public String getStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }

    public BigDecimal getValorTotal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValorTotal'");
    }
}
