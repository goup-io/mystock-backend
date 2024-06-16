package com.goup.services.produtos;

import com.goup.dtos.estoque.produtos.cor.CorMapper;
import com.goup.dtos.estoque.produtos.cor.CorReq;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.CorRepository;
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
public class CorServiceTest {

    @Mock
    CorRepository repository;

    @InjectMocks
    CorService service;

    @Test
    @DisplayName("Cadastrar uma nova cor com sucesso")
    void cadastrarNovaCorComSucesso() {
        CorReq corReq = new CorReq("Azul");
        Cor corSalva = new Cor(1, "Azul");

        when(repository.existsByNome(corReq.nome())).thenReturn(false);
        when(repository.save(any(Cor.class))).thenReturn(corSalva);

        Cor resposta = service.cadastrar(corReq);

        assertNotNull(resposta);
        assertEquals(corSalva.getId(), resposta.getId());
        assertEquals(corSalva.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Falhar ao cadastrar uma cor já existente")
    void falharAoCadastrarCorExistente() {
        CorReq corReq = new CorReq("Azul");

        when(repository.existsByNome(corReq.nome())).thenReturn(true);

        assertThrows(RegistroConflitanteException.class, () -> {
            service.cadastrar(corReq);
        });
    }

    @Test
    @DisplayName("Listar todas as cores")
    void listarTodasAsCores() {
        List<Cor> listaEsperada = List.of(new Cor(1, "Azul"), new Cor(2, "Vermelho"));
        when(repository.findAll()).thenReturn(listaEsperada);

        List<Cor> resposta = service.listar();

        assertNotNull(resposta);
        assertEquals(listaEsperada.size(), resposta.size());
        assertEquals(listaEsperada, resposta);
    }

    @Test
    @DisplayName("Buscar cor por ID existente")
    void buscarCorPorIdExistente() {
        Cor corEsperada = new Cor(1, "Azul");
        when(repository.findById(1)).thenReturn(Optional.of(corEsperada));

        Cor resposta = service.buscarPorId(1);

        assertNotNull(resposta);
        assertEquals(corEsperada.getId(), resposta.getId());
        assertEquals(corEsperada.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Buscar cor por ID inexistente")
    void buscarCorPorIdInexistente() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.buscarPorId(99);
        });
    }

    @Test
    @DisplayName("Atualizar cor existente com sucesso")
    void atualizarCorExistenteComSucesso() {
        Cor corExistente = new Cor(1, "Azul");
        CorReq corAtualizada = new CorReq("Verde");

        when(repository.findById(1)).thenReturn(Optional.of(corExistente));
        when(repository.save(any(Cor.class))).thenReturn(new Cor(1, "Verde"));

        Cor resposta = service.atualizar(1, corAtualizada);

        assertNotNull(resposta);
        assertEquals(1, resposta.getId());
        assertEquals("Verde", resposta.getNome());
    }

    @Test
    @DisplayName("Falhar ao atualizar cor inexistente")
    void falharAoAtualizarCorInexistente() {
        CorReq corAtualizada = new CorReq("Verde");

        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.atualizar(99, corAtualizada);
        });
    }

    @Test
    @DisplayName("Deletar cor existente com sucesso")
    void deletarCorExistenteComSucesso() {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);

        assertDoesNotThrow(() -> {
            service.remover(1);
        });
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Falhar ao deletar cor inexistente")
    void falharAoDeletarCorInexistente() {
        when(repository.existsById(99)).thenReturn(false);

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.remover(99);
        });
        verify(repository, never()).deleteById(99);
    }
}
