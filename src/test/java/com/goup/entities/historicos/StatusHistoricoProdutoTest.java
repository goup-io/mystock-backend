package com.goup.entities.historicos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusHistoricoProdutoTest {

    @Test
    void validarRetornoDescricao() {
        assertEquals(StatusHistoricoProduto.StatusHistorico.VENDIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Vendido"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.DEVOLVIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Devolvido"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.ABATIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Abatido"));
    }

    @Test
    void validarRetornoDescricaoMaiusculaMinuscula() {
        assertEquals(StatusHistoricoProduto.StatusHistorico.VENDIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("venDIdo"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.DEVOLVIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("devolvido"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.ABATIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("ABATIDO"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.ABATIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Abatido"));
    }
}