package com.goup.dtos.dashboards.dashboardGeral;

public record KpisRes(
        Double faturamentoMes,
        Double faturamentoDia,
        String modeloMaisVendido,
        String produtoMaisVendido,
        Integer produtosEmEstoque
) {
}
