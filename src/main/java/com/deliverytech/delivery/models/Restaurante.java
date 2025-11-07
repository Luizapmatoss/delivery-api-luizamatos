package com.deliverytech.delivery.models;

import com.deliverytech.delivery.dto.response.ProdutoResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do restaurante é obrigatório.")
    private String nome;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

    @NotNull
    private Boolean ativo = true;

    @NotNull(message = "A taxa de entrega é obrigatória.")
    @DecimalMin(value = "0.0", inclusive = true, message = "A taxa de entrega não pode ser negativa.")
    private BigDecimal taxaEntrega = BigDecimal.ZERO;

    @Min(0)
    @Max(5)
    private Double avaliacao = 0.0;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();

    public Restaurante() {}

    public Restaurante(String nome, String categoria, Boolean ativo, Double avaliacao, BigDecimal taxaEntrega) {
        this.nome = nome;
        this.categoria = categoria;
        this.ativo = ativo;
        this.avaliacao = avaliacao;
        this.taxaEntrega = taxaEntrega;
    }

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

    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public BigDecimal getTaxaEntrega() {
        return taxaEntrega;
    }
    public void setTaxaEntrega(BigDecimal taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", ativo=" + ativo +
                ", taxaEntrega=" + taxaEntrega +
                ", avaliacao=" + avaliacao +
                '}';
    }

    public String getDescricao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescricao'");
    }

    public BigDecimal getPreco() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPreco'");
    }

    public boolean getDisponivel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDisponivel'");
    }

    public ProdutoResponseDTO getRestaurante() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRestaurante'");
    }
}
