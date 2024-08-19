package com.goup.dtos.vendas.venda;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaReq;

import java.util.List;

public record TrocaEProdutosReq(TrocaReq trocaReq, List<ProdutoVendaReq> produtoVendaReqs) {
}
