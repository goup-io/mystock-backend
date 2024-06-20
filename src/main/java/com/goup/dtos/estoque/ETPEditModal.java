package com.goup.dtos.estoque;

import com.goup.entities.vendas.ItemPromocional;

public record ETPEditModal(
        Integer id,
        String codigo,
        String nome,
        String modelo,
        Integer tamanho,
        String cor,
        Double precoCusto,
        Double precoRevenda,
        Integer idLoja,
        Integer idProduto,
        ItemPromocional itemPromocional,
        Integer quantidade) {
}
