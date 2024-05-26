package com.goup.dtos.vendas.tipoVenda;

import com.goup.entities.vendas.TipoVenda;

import java.util.List;

public class TipoVendaMapper {
    public static TipoVendaRes entityToRes(TipoVenda tipoVenda){
        return new TipoVendaRes(tipoVenda.getId(), tipoVenda.getTipo().getTipo(), tipoVenda.getDesconto());
    }

    public static TipoVenda atualizarTipoVenda(TipoVendaReq tipoVendaReq, TipoVenda tipoVenda){
        tipoVenda.setTipo(tipoVendaReq.tipo());
        tipoVenda.setDesconto(tipoVendaReq.desconto());
        return tipoVenda;
    }

    public static TipoVenda atualizarDescontoTipoVenda(TipoVendaDescontoReq tipoVendaDescontoReq, TipoVenda tipoVenda){
        tipoVenda.setDesconto(tipoVendaDescontoReq.desconto());
        return tipoVenda;
    }



    public static List<TipoVendaRes> entityToResList(List<TipoVenda> tipoVendas){
        return tipoVendas.stream().map(TipoVendaMapper::entityToRes).toList();
    }
}
