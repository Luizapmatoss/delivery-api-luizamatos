package com.deliverytech.delivery.dto;

public class RelatorioProdutosMaisVendidosDTO {
    private String produto;
    private Integer quantidadeVendida;

    public RelatorioProdutosMaisVendidosDTO(String produto, Integer quantidadeVendida) {
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getProduto() {
        return produto;
    }

    public Integer getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
