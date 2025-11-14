package com.deliverytech.delivery.dto;

public class RelatorioClientesAtivosDTO {
    private String cliente;
    private Integer quantidadePedidos;

    public RelatorioClientesAtivosDTO(String cliente, Integer quantidadePedidos) {
        this.cliente = cliente;
        this.quantidadePedidos = quantidadePedidos;
    }

    public String getCliente() {
        return cliente;
    }

    public Integer getQuantidadePedidos() {
        return quantidadePedidos;
    }
}
