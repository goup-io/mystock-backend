package com.goup.dtos.estoque;

import com.goup.entities.vendas.ItemPromocional;

public record ETPBuscaRes(
        Integer id,
        String codigo,
        String produto,
        String modelo,
        Integer tamanho,
        String cor,
        Double valorCusto,
        Double valorRevenda,
        String loja,
        ItemPromocional itemPromocional,
        Integer quantidade,
        Integer idProduto
) {
}
