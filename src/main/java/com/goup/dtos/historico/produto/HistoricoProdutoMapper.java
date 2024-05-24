package com.goup.dtos.historico.produto;

import com.goup.entities.historicos.HistoricoProduto;
import com.goup.entities.historicos.StatusHistoricoProduto;
import com.goup.entities.vendas.ProdutoVenda;

import java.time.LocalDateTime;
import java.util.List;

public class HistoricoProdutoMapper {
    public static HistoricoProduto reqToEntity(StatusHistoricoProduto statusHistoricoProduto, ProdutoVenda produtoVenda){
        HistoricoProduto historicoProduto = new HistoricoProduto();
        historicoProduto.setProdutoVenda(produtoVenda);
        historicoProduto.setStatusHistoricoProduto(statusHistoricoProduto);
        historicoProduto.setDataHora(LocalDateTime.now());
        return historicoProduto;
    }

    public static HistoricoProdutoRes entitytoRes(HistoricoProduto historicoProduto){
        return new HistoricoProdutoRes(
                historicoProduto.getDataHora(),
                historicoProduto.getProdutoVenda(),
                historicoProduto.getStatusHistoricoProduto()
        );
    }

    public static List<HistoricoProdutoRes> listRes(List<HistoricoProduto> historicoProdutos){
        return historicoProdutos.stream().map(HistoricoProdutoMapper::entitytoRes).toList();
    }
}
