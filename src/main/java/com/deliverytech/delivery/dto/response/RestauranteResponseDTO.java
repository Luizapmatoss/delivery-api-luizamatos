package com.deliverytech.delivery.dto.response;

public class RestauranteResponseDTO {
    private Long id;
    private String nome;
    private String categoria;
    private Double avaliacao;
    private boolean ativo;

    public RestauranteResponseDTO() {}

    public RestauranteResponseDTO(Long id, String nome, String categoria, Double avaliacao, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getAvaliacao() { return avaliacao; }
    public void setAvaliacao(Double avaliacao) { this.avaliacao = avaliacao; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
