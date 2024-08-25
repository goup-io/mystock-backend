package com.goup.dtos.vendas.produtoVenda;

import com.goup.entities.vendas.ItemPromocional;

public record ProdutoVendaTrocaReq(
        Integer etpId,
        Integer quantidade,
        Double desconto,
        ItemPromocional itemPromocional
) {
}
