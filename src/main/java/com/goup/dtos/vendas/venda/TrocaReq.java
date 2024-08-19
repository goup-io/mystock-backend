package com.goup.dtos.vendas.venda;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TrocaReq(
        @DecimalMin("0.0") @NotBlank @Positive
        Double desconto,
        @NotNull
        Integer codigoVendedor
) {
}
