package com.goup.dtos.vendas.produtoVenda;

public record ProdutoVendaDetalhamentoRes(
    Integer id,
    String codigo,
    String descricao,
    Double preco,
    Integer qtd,
    Double descontoUnitario,
    Double precoLiquido,
    Double subtotal
){
}
