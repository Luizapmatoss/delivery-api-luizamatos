package com.deliverytech.delivery.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.deliverytech.delivery.dto.ItemPedidoDTO;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Um pedido pertence a um cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Um pedido pertence a um restaurante
    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    // Um pedido possui vários itens (relação com ItemPedido)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    // Valor total do pedido
    private BigDecimal valorTotal;

    private String status;
    private LocalDateTime dataCriacao;

    // ===== Construtores =====
    public Pedido() {
        this.dataCriacao = LocalDateTime.now();
        this.status = "PENDENTE";
        this.valorTotal = BigDecimal.ZERO;
    }

    // ===== Getters e Setters =====
    public Long getId() {
        return id;
    }
    
    public void setId(Long itens2) {
        this.id = itens2;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente2) {
        this.cliente = cliente2;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante2) {
        this.restaurante = restaurante2;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItem(List<ItemPedido> itens2) {
        this.itens = itens2;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // ===== Métodos utilitários =====
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
        item.setPedido(this);
        calcularValorTotal();
    }

    public void removerItem(ItemPedido item) {
        itens.remove(item);
        item.setPedido(null);
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = itens.stream()
        .map(ItemPedido::getSubtotal) 
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + (cliente != null ? cliente.getNome() : "null") +
                ", restaurante=" + (restaurante != null ? restaurante.getNome() : "null") +
                ", valorTotal=" + valorTotal +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

    public void setItens(List<ItemPedidoDTO> itens2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setItens'");
    }
}
