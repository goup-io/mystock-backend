package com.goup.dtos.vendas.tipoPagamento;

import com.goup.entities.vendas.TipoPagamento;

import java.util.List;

public class TipoPagamentoMapper {
    public static TipoPagamentoRes entityToRes(TipoPagamento tipoPagamento){
        return new TipoPagamentoRes(tipoPagamento.getId(), tipoPagamento.getMetodo());
    }
    public static List<TipoPagamentoRes> entityToResList(List<TipoPagamento> tipoPagamentos){
        return tipoPagamentos.stream().map(TipoPagamentoMapper::entityToRes).toList();
    }
}
