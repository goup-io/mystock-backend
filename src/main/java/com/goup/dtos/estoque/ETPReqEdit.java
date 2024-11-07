package com.goup.dtos.estoque;

import com.goup.entities.vendas.ItemPromocional;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ETPReqEdit(
        @NotNull String nome ,
        @NotNull @DecimalMin("0.1") Double valorCusto,
        @NotNull @DecimalMin("0.1") Double valorRevenda,
        @NotNull ItemPromocional itemPromocional,
        Integer quantidade,
        @NotNull String codigo) {
}