package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.modelos.ModeloMapper;
import com.goup.dtos.estoque.produtos.modelos.ModeloReq;
import com.goup.dtos.estoque.produtos.modelos.ModeloRes;
import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.repositories.produtos.CategoriaRepository;
import com.goup.repositories.produtos.ModeloRepository;
import com.goup.repositories.produtos.TipoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelos")
public class ModeloController {
    @Autowired
    private ModeloRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TipoRepository tipoRepository;


    @PostMapping
    public ResponseEntity<ModeloRes> cadastrar(@RequestBody @Valid ModeloReq modelo) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(modelo.idCategoria());
        Optional<Tipo> tipo = this.tipoRepository.findById(modelo.idTipo());
        boolean codigoExiste = this.repository.findByCodigo(modelo.codigo()).isPresent();

        if(categoria.isEmpty() || tipo.isEmpty()){
            return ResponseEntity.status(404).build();
        }else if(codigoExiste){
            return ResponseEntity.status(409).build();
        }

        Modelo entidade = ModeloMapper.reqToEntity(modelo, categoria.get(), tipo.get());
        final Modelo modeloSalvo = this.repository.save(ModeloMapper.reqToEntity(modelo, categoria.get(), tipo.get()));
        ModeloRes resposta = ModeloMapper.entityToRes(entidade);
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping
    public ResponseEntity<List<ModeloRes>> listar() {
        List<Modelo> modelos = this.repository.findAll();

        if (modelos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        List<ModeloRes> modelosRes = ModeloMapper.listToListRes(modelos);
        return ResponseEntity.status(200).body(modelosRes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloRes> buscarPorId(@PathVariable Integer id) {
        Optional<Modelo> modeloOpt = repository.findById(id);
        if(modeloOpt.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        ModeloRes modeloRes = ModeloMapper.entityToRes(modeloOpt.get());
        return ResponseEntity.status(200).body(modeloRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloRes> atualizar(@PathVariable int id, @RequestBody @Valid ModeloReq modelo) {
        Optional<Modelo> byId = repository.findById(id);
        return atualizar(byId, modelo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        if (repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ModeloRes> buscarPorCodigo(@PathVariable String codigo) {
        Optional<Modelo> modeloOpt = repository.findByCodigo(codigo);
        if(modeloOpt.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        ModeloRes modeloRes = ModeloMapper.entityToRes(modeloOpt.get());
        return ResponseEntity.status(200).body(modeloRes);
    }

    @PutMapping("/codigo/{codigo}")
    public ResponseEntity<ModeloRes> atualizarPorCodigo(@PathVariable String codigo, @RequestBody @Valid ModeloReq modelo) {
        Optional<Modelo> byId = repository.findByCodigo(codigo);
        return atualizar(byId, modelo);
    }


    @Transactional // precisa disso, não sei pq.. provavelmente por ser um delete personalizado, deve ter essa anotação
    @DeleteMapping("/codigo/{codigo}")
    public ResponseEntity<Void> removerPorCodigo(@PathVariable String codigo) {
        if (repository.findByCodigo(codigo).isPresent()) {
            this.repository.deleteByCodigo(codigo);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    // Método que dá pra ser utilizado igualmente nos dois verbos UPDATE, tanto por Codigo quanto por ID
    private ResponseEntity<ModeloRes> atualizar(Optional<Modelo> byId, ModeloReq modelo){
        if(byId.isPresent()){
            Optional<Categoria> categoria = this.categoriaRepository.findById(modelo.idCategoria());
            Optional<Tipo> tipo = this.tipoRepository.findById(modelo.idTipo());
            if(tipo.isPresent() && categoria.isPresent()){
                Modelo atualizado = ModeloMapper.updateEntity(byId.get(), modelo, categoria.get(), tipo.get());
                repository.save(atualizado);
                ModeloRes modeloRes = ModeloMapper.entityToRes(atualizado);
                return ResponseEntity.status(200).body(modeloRes);
            }
        }
        return ResponseEntity.status(404).build();
    }
}
