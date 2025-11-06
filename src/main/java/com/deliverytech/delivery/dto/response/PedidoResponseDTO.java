package com.deliverytech.delivery.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.deliverytech.delivery.dto.ItemPedidoDTO;

public class PedidoResponseDTO {
    private Long id;
    private Long clienteId;
    private String status;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;
    private List<ItemPedidoDTO> itens;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(Long id, Long clienteId, String status, BigDecimal valorTotal,
                             LocalDateTime dataCriacao, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.status = status;
        this.valorTotal = valorTotal;
        this.dataCriacao = dataCriacao;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public Long getClienteId() { 
        return clienteId; 
    }
    public void setClienteId(Long clienteId) { 
        this.clienteId = clienteId; 
    }

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    public BigDecimal getValorTotal() { 
        return valorTotal; 
    }
    public void setValorTotal(BigDecimal valorTotal) { 
        this.valorTotal = valorTotal; 
    }

    public LocalDateTime getDataCriacao() { 
        return dataCriacao; 
    }
    public void setDataCriacao(LocalDateTime dataCriacao) { 
        this.dataCriacao = dataCriacao; 
    }

    public List<ItemPedidoDTO> getItens() { 
        return itens; 
    }
    public void setItens(List<ItemPedidoDTO> itens) { 
        this.itens = itens; 
    }
}
