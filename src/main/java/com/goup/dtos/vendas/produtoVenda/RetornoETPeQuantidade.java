package com.goup.dtos.vendas.produtoVenda;

import com.goup.entities.estoque.ETP;

public record RetornoETPeQuantidade(ETP etp, Integer quantidade) {
}
