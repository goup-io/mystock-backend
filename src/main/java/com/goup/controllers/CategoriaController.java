package com.goup.controllers;

import com.goup.dtos.categoria.CategoriaMapper;
import com.goup.dtos.categoria.CategoriaReq;
import com.goup.entities.produtos.modelos.Categoria;
import com.goup.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// CRUD funcionando
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody CategoriaReq categoria) {
        final Categoria categoriaSalva = this.repository.save(CategoriaMapper.reqToEntity(categoria));
        return ResponseEntity.status(201).body(categoriaSalva);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = this.repository.findAll();

        if (categorias.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption)
        Optional<Categoria> categoriaOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre ou 404 caso contrário.
        return ResponseEntity.of(categoriaOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable int id, @RequestBody CategoriaReq categoriaAtualizada) {
        Optional<Categoria> categoriaOpt = repository.findById(id);
        if (categoriaOpt.isPresent()) {
            categoriaOpt.get().setNome(categoriaAtualizada.nome());
            this.repository.save(categoriaOpt.get());
            return ResponseEntity.status(200).body(categoriaOpt.get());
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        if (repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }
}
