package com.ecommerce.DTO;

public class UsuarioInput {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private UsuarioInput() {
    }

    public UsuarioInput(String nome, String cpf, String email, Long id) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.id = id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}