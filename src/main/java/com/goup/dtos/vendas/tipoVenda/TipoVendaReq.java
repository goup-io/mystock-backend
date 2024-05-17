package com.goup.dtos.vendas.tipoVenda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TipoVendaReq(@NotBlank @NotNull @Size(min = 3, max = 15) String tipo, @NotNull Double desconto) {
}
