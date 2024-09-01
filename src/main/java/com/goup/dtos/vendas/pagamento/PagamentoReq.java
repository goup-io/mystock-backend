package com.goup.dtos.vendas.pagamento;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoReq{
        @NotNull @DecimalMin("0.0")
        Integer idTipoPagamento;
        @NotNull @DecimalMin("0.0")
        Integer idVenda;
        @NotNull @DecimalMin("0.01")
        Double valor;
        @NotNull @DecimalMin("1.0")
        Integer qtdParcelas;
}


