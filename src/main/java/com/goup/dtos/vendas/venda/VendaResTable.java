package com.goup.dtos.vendas.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goup.entities.vendas.TipoVenda;

import java.time.LocalDate;
import java.time.LocalTime;

public record VendaResTable(
        Integer id,
        LocalDate data,
        @JsonFormat(pattern = "HH:mm:ss") LocalTime hora,
        String codigoVendedor,
        String nomeVendedor,
        TipoVenda tipoVenda,
        Integer qtdItens,
        Double valor,
        String statusVenda
)  {

}
