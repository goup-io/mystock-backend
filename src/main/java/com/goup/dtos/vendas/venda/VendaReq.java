package com.goup.dtos.vendas.venda;

import com.goup.entities.vendas.StatusVenda;
import jakarta.validation.constraints.*;

public record VendaReq(
        @DecimalMin("0.0") @NotBlank @Positive
        Double desconto,
        @NotNull
        Integer tipoVendaId,
        @NotNull
        Integer usuarioId
) {
}
