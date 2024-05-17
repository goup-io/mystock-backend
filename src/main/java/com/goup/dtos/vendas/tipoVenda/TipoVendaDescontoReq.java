package com.goup.dtos.vendas.tipoVenda;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record TipoVendaDescontoReq(@NotNull @DecimalMin("0.0") Double desconto) {
}
