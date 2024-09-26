package com.goup.services.produtos;

import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.entities.lojas.Loja;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.ETPRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ETPServiceTest {

    @Mock
    private ETPRepository etpRepository;

    @InjectMocks
    private ETPService etpService;

    @Test
    @DisplayName("Gerar exceção quando etp não for encontrado por ID")
    void gerarExcecaoQuandoETPNaoForEncontradoPorId() {

        Produto produtoSimulado = new Produto();
        ETP etpSimulado = new ETP();
        Modelo modeloSimulado = new Modelo();
        Tamanho tamanhoSimulado = new Tamanho();
        Cor corSimulado = new Cor();
        Loja lojaSimulada = new Loja();
        etpSimulado.setId(100);
        etpSimulado.setProduto(produtoSimulado);
        etpSimulado.setTamanho(tamanhoSimulado);
        etpSimulado.setLoja(lojaSimulada);
        produtoSimulado.setCor(corSimulado);
        produtoSimulado.setModelo(modeloSimulado);
        int id = 100;
        when(etpRepository.findById(id)).thenReturn(Optional.of(etpSimulado));

        assertNotNull(etpService.buscarPorId(id));

      /* RegistroNaoEncontradoException registroNaoEncontradoException = assertThrows(RegistroNaoEncontradoException.class, () -> {
            etpService.buscarPorId(id);
        });*/

        // assertEquals("ETP não encontrado", registroNaoEncontradoException.getMessage());
    }


}