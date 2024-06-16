package com.goup.services.produtos;

import com.goup.dtos.estoque.tamanho.TamanhoMapper;
import com.goup.dtos.estoque.tamanho.TamanhoReq;
import com.goup.entities.estoque.Tamanho;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.TamanhoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TamanhoServiceTest {

    @Mock
    TamanhoRepository repository;

    @InjectMocks
    TamanhoService service;

    @Test
    @DisplayName("Cadastrar um novo tamanho com sucesso")
    void cadastrarNovoTamanhoComSucesso() {
        TamanhoReq tamanhoReq = new TamanhoReq(42);
        Tamanho tamanhoSalvo = new Tamanho(1, 42);

        when(repository.existsByNumero(tamanhoReq.numero())).thenReturn(false);
        when(repository.save(any(Tamanho.class))).thenReturn(tamanhoSalvo);

        Tamanho resposta = service.cadastrar(tamanhoReq);

        assertNotNull(resposta);
        assertEquals(tamanhoSalvo.getId(), resposta.getId());
        assertEquals(tamanhoSalvo.getNumero(), resposta.getNumero());
    }

    @Test
    @DisplayName("Falhar ao cadastrar um tamanho já existente")
    void falharAoCadastrarTamanhoExistente() {
        TamanhoReq tamanhoReq = new TamanhoReq(42);

        when(repository.existsByNumero(tamanhoReq.numero())).thenReturn(true);

        assertThrows(RegistroConflitanteException.class, () -> {
            service.cadastrar(tamanhoReq);
        });
    }

    @Test
    @DisplayName("Listar todos os tamanhos")
    void listarTodosOsTamanhos() {
        List<Tamanho> listaEsperada = List.of(new Tamanho(1, 42), new Tamanho(2, 43));
        when(repository.findAll()).thenReturn(listaEsperada);

        List<Tamanho> resposta = service.listar();

        assertNotNull(resposta);
        assertEquals(listaEsperada.size(), resposta.size());
        assertEquals(listaEsperada, resposta);
    }

    @Test
    @DisplayName("Buscar tamanho por numeração existente")
    void buscarTamanhoPorNumeracaoExistente() {
        Tamanho tamanhoEsperado = new Tamanho(1, 42);
        when(repository.findByNumero(42)).thenReturn(Optional.of(tamanhoEsperado));

        Tamanho resposta = service.buscarPorNumeracao(42);

        assertNotNull(resposta);
        assertEquals(tamanhoEsperado.getId(), resposta.getId());
        assertEquals(tamanhoEsperado.getNumero(), resposta.getNumero());
    }

    @Test
    @DisplayName("Buscar tamanho por numeração inexistente")
    void buscarTamanhoPorNumeracaoInexistente() {
        when(repository.findByNumero(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.buscarPorNumeracao(99);
        });
    }

    @Test
    @DisplayName("Deletar tamanho existente por numeração")
    void deletarTamanhoExistentePorNumeracao() {
        when(repository.existsByNumero(42)).thenReturn(true);
        doNothing().when(repository).deleteByNumero(42);

        assertDoesNotThrow(() -> {
            service.deletar(42);
        });
        verify(repository, times(1)).deleteByNumero(42);
    }

    @Test
    @DisplayName("Falhar ao deletar tamanho inexistente por numeração")
    void falharAoDeletarTamanhoInexistentePorNumeracao() {
        when(repository.existsByNumero(99)).thenReturn(false);

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.deletar(99);
        });
        verify(repository, never()).deleteByNumero(99);
    }
}
