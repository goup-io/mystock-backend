package com.goup.dtos.vendas.produtoVenda;

import com.goup.entities.vendas.ItemPromocional;

public record ProdutoVendaDetalhamentoRes(
    Integer id,
    String codigo,
    String descricao,
    Double precoUnitario, // Preco bruto unitario
    Integer qtd, // Quantidade
    Double desconto, // Esse desconto é um só, caso queira o desconto que está sendo atribuido unitáriamente, o calculo é desconto / qtd
    Double precoLiquidoUnitario, // Preco liquido unitario, ou seja, preco - desconto
    Double totalBruto, // PreçoUnitario * qtd
    Double subtotal, // Preço liquido unitario * quantidade
    ItemPromocional itemPromocional
){
}
