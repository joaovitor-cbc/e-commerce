package com.ecommerce.model;

import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double preco;

    private int quantidade;

    private Produto() {
    }

    public Produto(Long id, String nome, Double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
