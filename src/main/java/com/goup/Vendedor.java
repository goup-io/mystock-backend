package com.goup;

public class Vendedor extends Usuario{

    private Double comissao;
    private Double valorTotalVendas;
    private Integer qtdVendas;


    public Vendedor() {
    }

    public Vendedor(int id, int codigoVenda, String nome, String cargo, String telefone, Double comissao) {
        super(id, codigoVenda, nome, cargo, telefone);
        this.comissao = comissao;
    }

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Double getValorTotalVendas() {
        return valorTotalVendas;
    }

    public void setValorTotalVendas(Double valorTotalVendas) {
        this.valorTotalVendas = valorTotalVendas;
    }

    public Integer getQtdVendas() {
        return qtdVendas;
    }

    public void setQtdVendas(Integer qtdVendas) {
        this.qtdVendas = qtdVendas;
    }
}
