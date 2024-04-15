package com.goup.controllers.produtos;

import com.goup.entities.produtos.modelos.Modelo;
import com.goup.repositories.produtos.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelos")
public class ModeloController {
    @Autowired
    private final ModeloRepository repository;

    public ModeloController(ModeloRepository repository) {
        this.repository = repository;
    }
    @PostMapping
    public ResponseEntity<Modelo> cadastrar(@RequestBody Modelo modelo) {
        final Modelo modeloSalvo = this.repository.save(modelo);
        return ResponseEntity.status(201).body(modeloSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Modelo>> listar() {
        List<Modelo> modelos = this.repository.findAll();

        if (modelos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption)
        Optional<Modelo> modeloOpt = repository.findById(id);

        // Retorna 200 com modelopo caso encontre ou 404 caso contrário.
        return ResponseEntity.of(modeloOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> atualizar(@PathVariable int id, @RequestBody Modelo modeloAtualizado) {
        if (repository.existsById(id)) {
            modeloAtualizado.setId(id);
            Modelo registrado = this.repository.save(modeloAtualizado);
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
