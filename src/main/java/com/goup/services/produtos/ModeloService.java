package com.goup.services.produtos;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {
    @Autowired
    private ModeloRepository repository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private TipoRepository tipoRepository;

    public ModeloRes cadastrar(ModeloReq modelo){
        Optional<Categoria> categoria = this.categoriaRepository.findById(modelo.idCategoria());
        Optional<Tipo> tipo = this.tipoRepository.findById(modelo.idTipo());
        boolean codigoExiste = this.repository.findByCodigo(modelo.codigo()).isPresent();

        if(categoria.isEmpty() || tipo.isEmpty()){
            throw new RegistroNaoEncontradoException("Categoria ou Tipo não encontrados");
        }else if(codigoExiste){
            throw new RegistroConflitanteException("Codigo já existente em outro modelo");
        }
        Modelo entidade = ModeloMapper.reqToEntity(modelo, categoria.get(), tipo.get());
        this.repository.save(ModeloMapper.reqToEntity(modelo, categoria.get(), tipo.get()));
        return ModeloMapper.entityToRes(entidade);
    }

    public List<ModeloRes> listarPorFiltro(
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String modelo,
        @RequestParam(required = false) String codigo)
    {
        List<Modelo> modelos = repository.findAllByFiltro(categoria, tipo, modelo, codigo);
        return ModeloMapper.listToListRes(modelos);
    }


    public ModeloRes buscarPorId(Integer id) {
        Optional<Modelo> modelo = repository.findById(id);
        if(modelo.isEmpty()){
            throw new RegistroNaoEncontradoException("Modelo não encontrado");
        }
        return ModeloMapper.entityToRes(modelo.get());
    }

    public ModeloRes atualizar(Integer id, ModeloReq modelo){
        Optional<Modelo> byId = repository.findById(id);
        if(byId.isPresent()){
            Optional<Categoria> categoria = this.categoriaRepository.findById(modelo.idCategoria());
            Optional<Tipo> tipo = this.tipoRepository.findById(modelo.idTipo());
            if(tipo.isPresent() && categoria.isPresent()){
                Modelo atualizado = ModeloMapper.updateEntity(byId.get(), modelo, categoria.get(), tipo.get());
                repository.save(atualizado);
                return ModeloMapper.entityToRes(atualizado);
            }
        }
        throw new RegistroNaoEncontradoException("Modelo não encontrado");
    }

    public void deletar(int id) {
        if(!repository.existsById(id)){
            throw new RegistroNaoEncontradoException("Modelo não encontrado");
        }
        repository.deleteById(id);
    }
}
