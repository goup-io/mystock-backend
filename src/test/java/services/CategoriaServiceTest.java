package services;

import com.goup.dtos.estoque.produtos.modelos.categoria.CategoriaMapper;
import com.goup.dtos.estoque.produtos.modelos.categoria.CategoriaReq;
import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.CategoriaRepository;
import com.goup.services.produtos.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CategoriaService service;

    @Test
    @DisplayName("Cadastrar uma nova categoria")
    void cadastrarNovaCategoria() {
        CategoriaReq categoriaReq = new CategoriaReq("Esporte");
        Categoria categoriaSalva = new Categoria(1, "Esporte");
        when(repository.save(any(Categoria.class))).thenReturn(categoriaSalva);

        Categoria resposta = service.cadastrar(categoriaReq);

        assertNotNull(resposta);
        assertEquals(categoriaSalva.getId(), resposta.getId());
        assertEquals(categoriaSalva.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Listar categorias quando não há categorias cadastradas")
    void listarCategoriasQuandoNaoHaCategoriasCadastradas() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Categoria> lista = service.listar();

        assertTrue(lista.isEmpty());
    }

    @Test
    @DisplayName("Listar categorias quando há categorias cadastradas")
    void listarCategoriasQuandoHaCategoriasCadastradas() {
        List<Categoria> listaEsperada = List.of(new Categoria(1, "Esporte"), new Categoria(2, "Moda"));
        when(repository.findAll()).thenReturn(listaEsperada);

        List<Categoria> resposta = service.listar();

        assertEquals(listaEsperada.size(), resposta.size());
        assertEquals(listaEsperada, resposta);
    }

    @Test
    @DisplayName("Buscar categoria por ID existente")
    void buscarCategoriaPorIdExistente() {
        int id = 1;
        Categoria categoriaEsperada = new Categoria(id, "Esporte");
        when(repository.findById(id)).thenReturn(Optional.of(categoriaEsperada));

        Categoria resposta = service.buscarPorId(id);

        assertNotNull(resposta);
        assertEquals(categoriaEsperada.getId(), resposta.getId());
        assertEquals(categoriaEsperada.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Buscar categoria por ID inexistente")
    void buscarCategoriaPorIdInexistente() {
        int id = 99;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.buscarPorId(id);
        });
    }

    @Test
    @DisplayName("Atualizar categoria existente")
    void atualizarCategoriaExistente() {
        int id = 1;
        Categoria categoriaExistente = new Categoria(id, "Esporte");
        CategoriaReq categoriaAtualizadaReq = new CategoriaReq("Saúde");
        Categoria categoriaAtualizada = new Categoria(id, "Saúde");

        when(repository.findById(id)).thenReturn(Optional.of(categoriaExistente));
        when(repository.save(any(Categoria.class))).thenReturn(categoriaAtualizada);

        Categoria resposta = service.atualizar(id, categoriaAtualizadaReq);

        assertNotNull(resposta);
        assertEquals(categoriaAtualizada.getId(), resposta.getId());
        assertEquals(categoriaAtualizada.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Atualizar categoria inexistente")
    void atualizarCategoriaInexistente() {
        int id = 99;
        CategoriaReq categoriaAtualizadaReq = new CategoriaReq("Saúde");

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.atualizar(id, categoriaAtualizadaReq);
        });
    }

    @Test
    @DisplayName("Remover categoria existente")
    void removerCategoriaExistente() {
        int id = 1;
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        assertDoesNotThrow(() -> {
            service.remover(id);
        });
    }

    @Test
    @DisplayName("Remover categoria inexistente")
    void removerCategoriaInexistente() {
        int id = 99;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.remover(id);
        });
    }
}
