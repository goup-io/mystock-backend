package com.goup.dtos.historico.produto;

import com.goup.entities.historicos.StatusHistoricoProduto;

public record HistoricoProdutoReq(Integer idProdutoVenda, StatusHistoricoProduto.StatusHistorico statusHistorico) {
}
