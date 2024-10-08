package com.goup.dtos.vendas.produtoVenda;

import com.goup.entities.vendas.ItemPromocional;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

public record ProdutoVendaReq(
        @NotNull @NotBlank
        Integer etpId,
        @NotNull @NotBlank @DecimalMin("1.0")
        Integer quantidade,
        @DecimalMin("0.0")
        Double desconto
) {
}
