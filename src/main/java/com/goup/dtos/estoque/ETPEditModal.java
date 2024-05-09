package com.goup.dtos.estoque;

public record ETPEditModal(Integer id, String codigo, String nome, String modelo, Integer tamanho, String cor, Double precoCusto, Double precoRevenda, Integer idLoja, Integer idProduto) {
}
