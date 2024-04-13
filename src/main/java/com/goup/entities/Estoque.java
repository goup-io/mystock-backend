package com.goup.entities;

import com.goup.entities.lojas.Loja;
import com.goup.entities.produtos.Produto;
import com.goup.entities.produtos.Tamanho;
import jakarta.persistence.*;

@Entity @Table
public class Estoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne @JoinColumn(name = "tamanho_id", referencedColumnName = "id")
    private Tamanho tamanho;

    @ManyToOne @JoinColumn(name="produto_id", referencedColumnName = "id")
    private Produto produto;

    @ManyToOne @JoinColumn(name = "loja_id", referencedColumnName = "id")
    private Loja loja;

    @Column
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}