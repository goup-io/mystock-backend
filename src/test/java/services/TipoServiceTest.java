package services;

import com.goup.dtos.estoque.produtos.modelos.tipo.TipoReq;
import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.TipoRepository;
import com.goup.services.produtos.TipoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TipoServiceTest {
    @Mock
    TipoRepository repository;

    @InjectMocks
    TipoService service;
    @Test @DisplayName("Listar tipos quando não há tipos cadastrados")
    void listarTiposQuandoNaoHaTiposCadastrados(){
        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<Tipo> lista = service.listar();
        assertTrue(lista.isEmpty());
    }

    @Test @DisplayName("Listar tipos quando há 3 tipos cadastrados")
    void listarTiposQuandoHaTresTiposCadastrados(){
        int tamanhoEsperado = 3;
        List<Tipo> listaEsperada = List.of(new Tipo(1, "Bota"), new Tipo(2, "Timberland"), new Tipo(3, "Coturno"));
        when(repository.findAll()).thenReturn(listaEsperada);
        List<Tipo> resposta = service.listar();
        assertEquals(listaEsperada.size(), resposta.size());
    }

    @Test
    @DisplayName("Cadastrar um novo tipo")
    void cadastrarNovoTipo() {
        TipoReq tipoReq = new TipoReq("Sandália");
        Tipo tipoSalvo = new Tipo(1, "Sandália");
        when(repository.save(any(Tipo.class))).thenReturn(tipoSalvo);

        Tipo resposta = service.cadastrar(tipoReq);

        assertNotNull(resposta);
        assertEquals(tipoSalvo.getId(), resposta.getId());
        assertEquals(tipoSalvo.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Buscar tipo por ID existente")
    void buscarTipoPorIdExistente() {
        int id = 1;
        Tipo tipoEsperado = new Tipo(id, "Tênis");
        when(repository.findById(id)).thenReturn(Optional.of(tipoEsperado));

        Tipo resposta = service.buscarPorId(id);

        assertNotNull(resposta);
        assertEquals(tipoEsperado.getId(), resposta.getId());
        assertEquals(tipoEsperado.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Buscar tipo por ID inexistente")
    void buscarTipoPorIdInexistente() {
        int id = 99;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.buscarPorId(id);
        });
    }

    @Test
    @DisplayName("Atualizar tipo existente")
    void atualizarTipoExistente() {
        int id = 1;
        Tipo tipoExistente = new Tipo(id, "Tênis");
        TipoReq tipoAtualizadoReq = new TipoReq("Sapato");
        Tipo tipoAtualizado = new Tipo(id, "Sapato");

        when(repository.findById(id)).thenReturn(Optional.of(tipoExistente));
        when(repository.save(any(Tipo.class))).thenReturn(tipoAtualizado);

        Tipo resposta = service.atualizar(id, tipoAtualizadoReq);

        assertNotNull(resposta);
        assertEquals(tipoAtualizado.getId(), resposta.getId());
        assertEquals(tipoAtualizado.getNome(), resposta.getNome());
    }

    @Test
    @DisplayName("Atualizar tipo inexistente")
    void atualizarTipoInexistente() {
        int id = 99;
        TipoReq tipoAtualizadoReq = new TipoReq("Sapato");

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.atualizar(id, tipoAtualizadoReq);
        });
    }

    @Test
    @DisplayName("Remover tipo existente")
    void removerTipoExistente() {
        int id = 1;
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);

        assertDoesNotThrow(() -> {
            service.remover(id);
        });
    }

    @Test
    @DisplayName("Remover tipo inexistente")
    void removerTipoInexistente() {
        int id = 99;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(RegistroNaoEncontradoException.class, () -> {
            service.remover(id);
        });
    }

}
