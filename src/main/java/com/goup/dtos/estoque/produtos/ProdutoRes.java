package com.goup.dtos.estoque.produtos;

public record ProdutoRes(int id, String cor, String modelo, String nome, double valorCusto, double valorRevenda) {
    public String getNome(){
        if(nome.isEmpty()){
            return this.modelo() + " " + this.cor();
        }
        return nome;
    }
}
