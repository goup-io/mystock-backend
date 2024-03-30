package com.goup.controllers;

import com.goup.entities.produtos.modelos.Categoria;
import com.goup.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody Categoria cor) {
        // ID
        if(false){
            return ResponseEntity.status(401).build(); // Se não houver permissão
        }

        if(false){
            return ResponseEntity.status(400).build(); // Se os dados foram enviados incorretamente;
        }

        final Categoria corSalvo = this.repository.save(cor);
        return ResponseEntity.status(201).body(corSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> cors = this.repository.findAll();

        if (cors.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption) caixa
        Optional<Categoria> corOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre
        // ou 404 caso contrário.
        return ResponseEntity.of(corOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable int id, @RequestBody Categoria corAtualizado) {
        if (repository.existsById(id)) {
            corAtualizado.setId(id);
            Categoria registrado = this.repository.save(corAtualizado);
            return ResponseEntity.status(200).body(registrado);
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
