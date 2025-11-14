package com.deliverytech.delivery.dto;

public class RelatorioVendasPorRestauranteDTO {
    private String restaurante;
    private Integer totalVendas;

    public RelatorioVendasPorRestauranteDTO(String restaurante, Integer totalVendas) {
        this.restaurante = restaurante;
        this.totalVendas = totalVendas;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public Integer getTotalVendas() {
        return totalVendas;
    }
}
