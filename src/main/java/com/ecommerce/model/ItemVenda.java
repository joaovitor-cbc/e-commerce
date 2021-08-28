package com.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "itemvenda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private int quantidade;

    @OneToOne
    private Venda venda;

    private ItemVenda() {
    }

    public ItemVenda(Long id, Produto produto, int quantidade, Venda venda) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.venda = venda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
}
