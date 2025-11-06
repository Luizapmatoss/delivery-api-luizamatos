package com.deliverytech.delivery.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public ItemPedido() {}

    public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, BigDecimal precoUnitario) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    // Getters e Setters
    public Long getId() { 
        return id; 
    }

    public Pedido getPedido() { 
        return pedido; 
    }
    public void setPedido(Pedido pedido) { 
        this.pedido = pedido; 
    }
    public Produto getProduto() { 
        return produto; 
    }
    public void setProduto(Produto produto) { 
        this.produto = produto; 
    }

    public Integer getQuantidade() { 
        return quantidade; 
    }
    public void setQuantidade(Integer quantidade) { 
        this.quantidade = quantidade; 
    }

    public BigDecimal getPrecoUnitario() { 
        return precoUnitario; 
    }
    public void setPrecoUnitario(BigDecimal precoUnitario) { 
        this.precoUnitario = precoUnitario; 
    }
    
    public BigDecimal getSubtotal() { 
        return subtotal; 
    }
    public void setSubtotal(BigDecimal subtotal) { 
        this.subtotal = subtotal; 
    }
}
