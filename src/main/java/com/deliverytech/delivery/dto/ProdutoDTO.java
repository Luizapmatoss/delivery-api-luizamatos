package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO utilizado para transferência de dados relacionados ao Produto.
 * 
 * Aplica validações para garantir que as informações recebidas
 * sejam consistentes e estejam de acordo com as regras de negócio:
 * - Nome e categoria obrigatórios
 * - Descrição com limite de caracteres
 * - Preço maior que zero
 */
public class ProdutoDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome; // Nome do produto cadastrado

    @Size(max = 255, message = "A descrição pode ter no máximo 255 caracteres.")
    private String descricao; // Descrição opcional, com limite definido

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria; // Categoria do produto (ex: Lanche, Bebida)

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    private BigDecimal preco; // Preço do produto, validado

    private Boolean disponivel = true; 
    // Indica se o produto está ativo para venda. Padrão = disponível.

    // Construtor vazio para facilitar a desserialização
    public ProdutoDTO() {}

    // Construtor completo
    public ProdutoDTO(String nome, String descricao, String categoria, BigDecimal preco, Boolean disponivel) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    // Getters e Setters (necessários para frameworks como Jackson)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
