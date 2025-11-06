package com.deliverytech.delivery.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResumoDTO {
    private Long id;
    private String status;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;

    public PedidoResumoDTO() {}

    public PedidoResumoDTO(Long id, String status, BigDecimal valorTotal, LocalDateTime dataCriacao) {
        this.id = id;
        this.status = status;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}
