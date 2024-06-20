package com.goup.dtos.vendas.venda;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaReq;

import java.util.List;

public record VendaEProdutosReq(VendaReq vendaReq, List<ProdutoVendaReq> produtosVendaReq){
}
