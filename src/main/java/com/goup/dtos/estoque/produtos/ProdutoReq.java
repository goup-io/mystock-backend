package com.goup.dtos.estoque.produtos;

public record ProdutoReq(int idCor, int idModelo, String nome, double valorCusto, double valorRevenda, Integer tamanho, Integer idLoja){}
