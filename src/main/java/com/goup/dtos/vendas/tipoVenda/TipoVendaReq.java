package com.goup.dtos.vendas.tipoVenda;

import com.goup.entities.vendas.TipoVenda;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TipoVendaReq(@NotBlank @NotNull TipoVenda.Tipo tipo, @NotNull Double desconto) {
}
