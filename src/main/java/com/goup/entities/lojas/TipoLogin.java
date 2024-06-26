package com.goup.entities.lojas;

public enum TipoLogin {
    CAIXA("Caixa"),
    AREA_VENDA("Área de Vendas");

    private String tipo;
    private String descricao;

    TipoLogin(String role) {
        this.tipo = role;
    }

    public String getRole() {
        return tipo;
    }
}
