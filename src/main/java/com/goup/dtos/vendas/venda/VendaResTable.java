package com.goup.dtos.vendas.venda;

import java.time.LocalDate;
import java.time.LocalTime;

public record VendaResTable(Integer id, LocalDate data, LocalTime hora, String vendedor, Integer qtdItens, Double valor, String statusVenda)  {
}
