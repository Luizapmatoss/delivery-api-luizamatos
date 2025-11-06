package com.deliverytech.delivery.dto.response;

public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private boolean ativo;

    public ClienteResponseDTO() {}

    public ClienteResponseDTO(Long id, String nome, String email, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
