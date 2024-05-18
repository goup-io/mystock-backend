package com.goup.dtos.vendas.produtoVenda;


import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.vendas.venda.VendaRes;

public record ProdutoVendaRes(
        Integer id,
        Double valorUnitario,
        Integer quantidade,
        Double desconto,
        String itemPromocional,
        VendaRes venda,
        ETPTableRes etp
        ) {
}
