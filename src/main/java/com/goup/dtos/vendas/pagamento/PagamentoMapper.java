package com.goup.dtos.vendas.pagamento;

import com.goup.dtos.vendas.venda.VendaMapper;
import com.goup.entities.vendas.Pagamento;
import com.goup.entities.vendas.TipoPagamento;
import com.goup.entities.vendas.Venda;

import java.util.List;

public class PagamentoMapper {
    public static Pagamento dtoToEntity(PagamentoReq pagamentoReq, TipoPagamento tipoPagamento, Venda venda){
        Pagamento pagamento = new Pagamento();
        pagamento.setTipoPagamento(tipoPagamento);
        pagamento.setValor(pagamentoReq.valor());
        pagamento.setQtdParcelas(pagamentoReq.qtdParcelas());
        pagamento.setVenda(venda);
        return pagamento;
    }

    public static PagamentoRes entityToDto(Pagamento pagamento){
        return new PagamentoRes(
                pagamento.getTipoPagamento().getMetodo().getMetodo(),
                VendaMapper.entityToRes(pagamento.getVenda()),
                pagamento.getValor(),
                pagamento.getQtdParcelas(),
                pagamento.getVenda().getValorTotal() - pagamento.getValor(),
                pagamento.getValor() - pagamento.getVenda().getValorTotal()
        );
    }

    public static List<PagamentoRes> pagamentoResList(List<Pagamento> pagamentos){
        return pagamentos.stream().map(PagamentoMapper::entityToDto).toList();
    }
}
