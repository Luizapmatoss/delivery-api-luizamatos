package com.deliverytech.delivery.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "produtos")
public class Produto {

    private Boolean disponivel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    @Positive(message = "O preço deve ser maior que zero.")
    private double preco;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    public Produto(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Produto(String nome, String categoria, double preco, Boolean disponivel, String descricao, Restaurante restaurante) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.disponivel = disponivel;
        this.descricao = descricao;
        this.restaurante = restaurante;
    }

    public Produto() {

    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                ", disponivel=" + disponivel +
                ", descricao='" + descricao + '\'' +
                ", restaurante=" + (restaurante != null ? restaurante.getNome() : "null") +
                '}';
    }
}
