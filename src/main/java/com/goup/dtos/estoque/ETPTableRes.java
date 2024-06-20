package com.goup.dtos.estoque;

import com.goup.entities.vendas.ItemPromocional;

public record ETPTableRes(
        Integer id,
        String codigo,
        String nome,
        String modelo,
        Integer tamanho,
        String cor,
        Double preco,
        String loja,
        ItemPromocional itemPromocional,
        Integer quantidade,
        Integer idProduto
) {
}
