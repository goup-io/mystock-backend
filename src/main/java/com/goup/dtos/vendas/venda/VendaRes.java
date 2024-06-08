package com.goup.dtos.vendas.venda;

import java.time.LocalDateTime;

public record VendaRes(Integer id, LocalDateTime dataHora, Double desconto, Double valorTotal, String status, String tipoVenda, String vendedor) {
}