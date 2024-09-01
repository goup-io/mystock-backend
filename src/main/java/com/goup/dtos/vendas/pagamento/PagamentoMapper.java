package com.goup.dtos.vendas.pagamento;

import com.goup.dtos.vendas.venda.VendaMapper;
import com.goup.entities.vendas.Pagamento;
import com.goup.entities.vendas.TipoPagamento;
import com.goup.entities.vendas.Venda;

import java.util.List;

public class PagamentoMapper {
    public static Pagamento dtoToEntity(PagamentoReq pagamentoReq, Double valorPagar, TipoPagamento tipoPagamento, Venda venda){
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setValor(valorPagar);
        pagamento.setQtdParcelas(pagamentoReq.getQtdParcelas());
        pagamento.setVenda(venda);
        return pagamento;
    }



    public static PagamentoRes entityToDto(Pagamento pagamento, Double valorPago){
        return new PagamentoRes(
                pagamento.getTipoPagamento().getMetodo().getMetodo(),
                VendaMapper.entityToRes(pagamento.getVenda()),
                pagamento.getValor(),
                pagamento.getQtdParcelas(),
                valorPago
        );
    }

    public static PagamentoRes entityToDto(Pagamento pagamento){
        return new PagamentoRes(
                pagamento.getTipoPagamento().getMetodo().getMetodo(),
                VendaMapper.entityToRes(pagamento.getVenda()),
                pagamento.getValor(),
                pagamento.getQtdParcelas(),
                pagamento.getValor()
        );
    }

    public static PagamentoFluxoRes entityToFluxoDto(Pagamento pagamento){
        return new PagamentoFluxoRes(
                pagamento.getTipoPagamento().getMetodo().getMetodo(),
                pagamento.getValor(),
                pagamento.getQtdParcelas()
        );
    }

    public static List<PagamentoRes> pagamentoResList(List<Pagamento> pagamentos){
        return pagamentos.stream().map(PagamentoMapper::entityToDto).toList();
    }

    public static List<PagamentoFluxoRes> pagamentoFluxoResList(List<Pagamento> pagamentos){
        return pagamentos.stream().map(PagamentoMapper::entityToFluxoDto).toList();
    }

}
