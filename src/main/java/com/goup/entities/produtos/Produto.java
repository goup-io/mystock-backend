package com.goup.entities.produtos;

import com.goup.entities.produtos.modelos.Modelo;
import com.goup.multiple_pk.ProdutoPK;
import jakarta.persistence.*;

@Entity
@Table
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne @JoinColumn(name="cor_id", referencedColumnName="id")
    private Cor cor;

    @ManyToOne @JoinColumns({
            @JoinColumn(name="modelo_id", referencedColumnName="id"),
            @JoinColumn(name="modelo_codigo", referencedColumnName="codigo")
    })
    private Modelo modelo;

    @Column
    private double valorCusto;

    @Column
    private double valorRevenda;

    @Column
    private double valorAtacado;

    @Column
    private double valorCaixa;

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

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public double getValorRevenda() {
        return valorRevenda;
    }

    public void setValorRevenda(double valorRevenda) {
        this.valorRevenda = valorRevenda;
    }

    public double getValorAtacado() {
        return valorAtacado;
    }

    public void setValorAtacado(double valorAtacado) {
        this.valorAtacado = valorAtacado;
    }

    public double getValorCaixa() {
        return valorCaixa;
    }

    public void setValorCaixa(double valorCaixa) {
        this.valorCaixa = valorCaixa;
    }
}