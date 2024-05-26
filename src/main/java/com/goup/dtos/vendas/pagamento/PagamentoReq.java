package com.goup.dtos.vendas.pagamento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoReq(
        @NotNull @NotBlank @DecimalMin("0.0")
        Integer idTipoPagamento,
        @NotNull @NotBlank @DecimalMin("0.0")
        Integer idVenda,
        @NotNull @NotBlank @DecimalMin("0.0")
        Double valor,
        @NotNull @NotBlank @DecimalMin("1.0")
        Integer qtdParcelas) {
}
