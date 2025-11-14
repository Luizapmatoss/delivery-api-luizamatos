package com.deliverytech.delivery.dto;

import java.time.LocalDate;

public class RelatorioPedidosPeriodoDTO {
    private LocalDate inicio;
    private LocalDate fim;
    private Integer totalPedidos;

    public RelatorioPedidosPeriodoDTO(LocalDate inicio, LocalDate fim, Integer totalPedidos) {
        this.inicio = inicio;
        this.fim = fim;
        this.totalPedidos = totalPedidos;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public Integer getTotalPedidos() {
        return totalPedidos;
    }
}
