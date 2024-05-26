package com.goup.dtos.vendas.pagamento;

import com.goup.dtos.vendas.venda.VendaRes;

public record PagamentoRes(String tipoPagamento, VendaRes vendaRes, Double valor, Integer qtdParcelas) {
}
