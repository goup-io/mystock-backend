package services;

import com.goup.dtos.estoque.produtos.modelos.ModeloMapper;
import com.goup.dtos.estoque.produtos.modelos.ModeloReq;
import com.goup.dtos.estoque.produtos.modelos.ModeloRes;
import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.CategoriaRepository;
import com.goup.repositories.produtos.ModeloRepository;
import com.goup.repositories.produtos.TipoRepository;
import com.goup.services.produtos.ModeloService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModeloServiceTest {

    @Mock
    ModeloRepository repository;

    @Mock
    CategoriaRepository categoriaRepository;

    @Mock
    TipoRepository tipoRepository;

    @InjectMocks
    ModeloService service;

    @Test
    @DisplayName("Cadastrar um novo modelo")
    void cadastrarNovoModelo() {
        Categoria categoria = new Categoria(1, "Categoria");
        Tipo tipo = new Tipo(1, "Tipo");
        ModeloReq modeloReq = new ModeloReq("Codigo", "Modelo", 1, 1);
        Modelo modeloSalvo = new Modelo(1, categoria, tipo, "Codigo", "Modelo");

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(tipoRepository.findById(1)).thenReturn(Optional.of(tipo));
        when(repository.findByCodigo("Codigo")).thenReturn(Optional.empty());
        when(repository.save(any(Modelo.class))).thenReturn(modeloSalvo);

        ModeloRes resposta = service.cadastrar(modeloReq);

        assertNotNull(resposta);
        assertEquals(modeloSalvo.getCodigo(), resposta.codigo());
        assertEquals(modeloSalvo.getNome(), resposta.nome());
    }

    @Test
    @DisplayName("Cadastrar um novo modelo com categoria ou tipo inexistente")
    void cadastrarNovoModeloCategoriaOuTipoInexistente() {
        ModeloReq modeloReq = new ModeloReq("Codigo", "Modelo", 1, 1);

        when(categoriaRepository.findById(1)).thenReturn(Optional.empty());
        when(tipoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.cadastrar(modeloReq);
        });
    }

    @Test
    @DisplayName("Cadastrar um novo modelo com código já existente")
    void cadastrarNovoModeloCodigoExistente() {
        Categoria categoria = new Categoria(1, "Categoria");
        Tipo tipo = new Tipo(1, "Tipo");
        ModeloReq modeloReq = new ModeloReq("Codigo", "Modelo", 1, 1);

        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(tipoRepository.findById(1)).thenReturn(Optional.of(tipo));
        when(repository.findByCodigo("Codigo")).thenReturn(Optional.of(new Modelo()));

        assertThrows(RegistroConflitanteException.class, () -> {
            service.cadastrar(modeloReq);
        });
    }

    @Test
    @DisplayName("Listar modelos por filtro")
    void listarModelosPorFiltro() {
        Categoria categoria = new Categoria(1, "Categoria");
        Tipo tipo = new Tipo(1, "Tipo");
        List<Modelo> listaEsperada = List.of(new Modelo(1, categoria, tipo, "Codigo1", "Modelo1"));
        when(repository.findAllByFiltro(anyString(), anyString(), anyString(), anyString())).thenReturn(listaEsperada);

        List<ModeloRes> resposta = service.listarPorFiltro("Categoria", "Tipo", "Modelo1", "Codigo1");

        assertNotNull(resposta);
        assertEquals(listaEsperada.size(), resposta.size());
    }

    @Test
    @DisplayName("Buscar modelo por ID existente")
    void buscarModeloPorIdExistente() {
        Categoria categoria = new Categoria(1, "Categoria");
        Tipo tipo = new Tipo(1, "Tipo");
        Modelo modeloEsperado = new Modelo(1, categoria, tipo, "Codigo", "Modelo");
        when(repository.findById(1)).thenReturn(Optional.of(modeloEsperado));

        ModeloRes resposta = service.buscarPorId(1);

        assertNotNull(resposta);
        assertEquals(modeloEsperado.getId(), resposta.id());
        assertEquals(modeloEsperado.getCodigo(), resposta.codigo());
        assertEquals(modeloEsperado.getNome(), resposta.nome());
    }

    @Test
    @DisplayName("Buscar modelo por ID inexistente")
    void buscarModeloPorIdInexistente() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.buscarPorId(99);
        });
    }

    @Test
    @DisplayName("Atualizar modelo existente")
    void atualizarModeloExistente() {
        Categoria categoria = new Categoria(1, "Categoria");
        Tipo tipo = new Tipo(1, "Tipo");
        Modelo modeloExistente = new Modelo(1, categoria, tipo, "Codigo", "Modelo");
        ModeloReq modeloAtualizadoReq = new ModeloReq("Codigo Atualizado", "Modelo Atualizado", 1, 1);
        Modelo modeloAtualizado = new Modelo(1, categoria, tipo, "Codigo Atualizado", "Modelo Atualizado");

        when(repository.findById(1)).thenReturn(Optional.of(modeloExistente));
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoria));
        when(tipoRepository.findById(1)).thenReturn(Optional.of(tipo));
        when(repository.save(any(Modelo.class))).thenReturn(modeloAtualizado);

        ModeloRes resposta = service.atualizar(1, modeloAtualizadoReq);

        assertNotNull(resposta);
        assertEquals(modeloAtualizado.getId(), resposta.id());
        assertEquals(modeloAtualizado.getCodigo(), resposta.codigo());
        assertEquals(modeloAtualizado.getNome(), resposta.nome());
    }

    @Test
    @DisplayName("Atualizar modelo inexistente")
    void atualizarModeloInexistente() {
        ModeloReq modeloAtualizadoReq = new ModeloReq("Codigo Atualizado", "Modelo Atualizado", 1, 1);

        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.atualizar(99, modeloAtualizadoReq);
        });
    }

    @Test
    @DisplayName("Deletar modelo existente")
    void deletarModeloExistente() {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);

        assertDoesNotThrow(() -> {
            service.deletar(1);
        });
    }

    @Test
    @DisplayName("Deletar modelo inexistente")
    void deletarModeloInexistente() {
        when(repository.existsById(99)).thenReturn(false);

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.deletar(99);
        });
    }
}
