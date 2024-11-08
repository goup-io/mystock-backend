package com.goup.dtos.vendas.venda;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record VendaRes(
        Integer id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime dataHora,
        Double desconto,
        Double valorTotal,
        String status,
        String tipoVenda,
        String vendedor) {


}