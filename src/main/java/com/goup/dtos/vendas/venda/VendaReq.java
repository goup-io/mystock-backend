package com.goup.dtos.vendas.venda;

import com.goup.entities.vendas.StatusVenda;
import jakarta.validation.constraints.*;

public record VendaReq(
        @DecimalMin("0.0") @NotBlank @Positive
        Double desconto,
        @DecimalMin("0.0") @NotBlank @NotNull @Positive
        Double valorTotal,
        @NotNull @NotBlank
        StatusVenda statusVenda,
        @NotNull
        Integer tipoVendaId,
        @NotNull
        Integer usuarioId
) {
}
