package com.goup.dtos.vendas.pagamento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoReq(
        @NotNull @DecimalMin("0.0")
        Integer idTipoPagamento,
        @NotNull @DecimalMin("0.0")
        Integer idVenda,
        @NotNull @DecimalMin("0.0")
        Double valor,
        @NotNull @DecimalMin("1.0")
        Integer qtdParcelas) {
}
