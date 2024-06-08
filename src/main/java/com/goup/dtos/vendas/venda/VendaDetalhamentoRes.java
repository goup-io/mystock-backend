package com.goup.dtos.vendas.venda;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaDetalhamentoRes;
import com.goup.dtos.vendas.produtoVenda.ProdutoVendaRes;
import com.goup.entities.vendas.TipoVenda;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record VendaDetalhamentoRes(
    Integer id,
    LocalDate data,
    LocalTime hora,
    String nomeVendedor,
    TipoVenda tipoVenda,
    Integer qtdItens,
    Double valor,
    String statusVenda,
    List<ProdutoVendaDetalhamentoRes> produtosVenda
) {
}
