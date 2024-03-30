package com.goup.controllers;

import com.goup.entities.produtos.Cor;
import com.goup.repositories.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cores")
public class CorController {
    @Autowired
    private final CorRepository repository;

    public CorController(CorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Cor> cadastrar(@RequestBody Cor cor) {
        // ID
        if(false){
            return ResponseEntity.status(401).build(); // Se não houver permissão
        }

        if(false){
            return ResponseEntity.status(400).build(); // Se os dados foram enviados incorretamente;
        }

        final Cor corSalvo = this.repository.save(cor);
        return ResponseEntity.status(201).body(corSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Cor>> listar() {
        List<Cor> cors = this.repository.findAll();

        if (cors.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cor> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption) caixa
        Optional<Cor> corOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre
        // ou 404 caso contrário.
        return ResponseEntity.of(corOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cor> atualizar(@PathVariable int id, @RequestBody Cor corAtualizado) {
        if (repository.existsById(id)) {
            corAtualizado.setId(id);
            Cor registrado = this.repository.save(corAtualizado);
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

