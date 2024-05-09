package com.goup.dtos.estoque;

public record ETPTableRes(Integer id, String codigo, String nome, String modelo, Integer tamanho, String cor, Double preco, String loja,  Integer quantidade) {
}
