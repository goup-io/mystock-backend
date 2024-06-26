package com.goup.dtos.historico.produto;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.vendas.venda.VendaMapper;
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
        HistoricoProdutoRes historicoProdutoRes = new HistoricoProdutoRes();
        historicoProdutoRes.setId(historicoProduto.getId());
        historicoProdutoRes.setDataHora(historicoProduto.getDataHora());
        historicoProdutoRes.setStatusHistoricoProduto(historicoProduto.getStatusHistoricoProduto());
        if (historicoProduto.getProdutoVenda() != null){
            HistoricoProdutoRes.ProdutoVendaListagem produtoVendaListagem = new HistoricoProdutoRes.ProdutoVendaListagem();
            produtoVendaListagem.setId(historicoProduto.getProdutoVenda().getId());
            produtoVendaListagem.setValorUnitario(historicoProduto.getProdutoVenda().getValorUnitario());
            produtoVendaListagem.setQuantidade(historicoProduto.getProdutoVenda().getQuantidade());
            produtoVendaListagem.setDesconto(historicoProduto.getProdutoVenda().getDesconto());
//            produtoVendaListagem.setItemPromocional(historicoProduto.getProdutoVenda().getEtp().getItemPromocional().name());


//            produtoVendaListagem.setItemPromocional(historicoProduto.getProdutoVenda(). getItemPromocional().name());
            produtoVendaListagem.setEtp(ETPMapper.toTableResponseEntity(historicoProduto.getProdutoVenda().getEtp()));
            produtoVendaListagem.setVenda(VendaMapper.entityToRes(historicoProduto.getProdutoVenda().getVenda()));
            historicoProdutoRes.setProdutoVenda(produtoVendaListagem);
        }
        return historicoProdutoRes;
    }

    public static List<HistoricoProdutoRes> listRes(List<HistoricoProduto> historicoProdutos){
        return historicoProdutos.stream().map(HistoricoProdutoMapper::entitytoRes).toList();
    }
}
