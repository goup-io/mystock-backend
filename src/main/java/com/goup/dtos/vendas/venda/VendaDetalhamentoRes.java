package com.goup.dtos.vendas.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data,
    @JsonFormat(pattern = "HH:mm:ss") LocalTime hora,
    String nomeVendedor,
    String tipoVenda,
    Double descontoTipoVenda,
    Integer qtdItens,
    Double valorBruto,
    Double descontoProdutos,
    Double valorLiquido,
    Double descontoVenda,
    Double valorTotal,
    String statusVenda,
    List<ProdutoVendaDetalhamentoRes> produtosVenda
) {
}
