package com.goup.dtos.estoque.produtos;

public record ProdutoEditReq(Integer idCor, Integer idModelo, String nome, Double precoCusto, Double precoRevenda) {
}
