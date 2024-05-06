package com.goup.multiple_pk;

import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.modelos.Modelo;

import java.io.Serializable;

public class ProdutoPK implements Serializable {
    private int id;
    private Cor cor;
    private Modelo modelo;

    public ProdutoPK() {}

    public ProdutoPK(int id, Cor cor, Modelo modelo) {
        this.id = id;
        this.cor = cor;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
