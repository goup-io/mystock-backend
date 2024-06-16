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
import com.goup.services.produtos.ModeloService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelos")
public class ModeloController {
    @Autowired
    private ModeloService service;

    @PostMapping
    public ResponseEntity<ModeloRes> cadastrar(@RequestBody @Valid ModeloReq modelo) {
        return ResponseEntity.status(201).body(service.cadastrar(modelo));
    }

    @GetMapping
    public ResponseEntity<List<ModeloRes>> listarPorFiltro(
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String modelo,
        @RequestParam(required = false) String codigo
    ){
        List<ModeloRes> modelos = service.listarPorFiltro(categoria, tipo, modelo, codigo);
        return modelos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloRes> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloRes> atualizar(@PathVariable int id, @RequestBody @Valid ModeloReq modelo) {
        return ResponseEntity.status(200).body(service.atualizar(id, modelo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
