package com.goup.dtos.historico.produto;

import com.goup.entities.historicos.StatusHistoricoProduto;
import com.goup.entities.vendas.ProdutoVenda;

import java.time.LocalDateTime;

public record HistoricoProdutoReq(Integer idProdutoVenda, Integer idStatus) {
}
