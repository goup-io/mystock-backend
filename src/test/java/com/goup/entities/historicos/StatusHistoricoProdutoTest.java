package com.goup.entities.historicos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusHistoricoProdutoTest {

    @Test
    @DisplayName("Validar retorno de status por descrição")
    void validarRetornoDescricao() {
        assertEquals(StatusHistoricoProduto.StatusHistorico.VENDIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Vendido"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.DEVOLVIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Devolvido"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.ABATIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Abatido"));
    }

    @Test
    @DisplayName("Validar retorno de status por descrição com letras maiúsculas, minúsculas ou ambas")
    void validarRetornoDescricaoMaiusculaMinuscula() {
        assertEquals(StatusHistoricoProduto.StatusHistorico.VENDIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("venDIdo"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.DEVOLVIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("devolvido"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.ABATIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("ABATIDO"));
        assertEquals(StatusHistoricoProduto.StatusHistorico.ABATIDO, StatusHistoricoProduto.StatusHistorico.fromDescricao("Abatido"));
    }
}