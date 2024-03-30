package com.goup.controllers;

import com.goup.entities.produtos.modelos.Tipo;
import com.goup.repositories.TipoRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos")
public class TipoController {
    @Autowired
    private final TipoRepository repository;

    public TipoController(TipoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Tipo> cadastrar(@RequestBody Tipo tipo) {
        // ID
        if(false){
            return ResponseEntity.status(401).build(); // Se não houver permissão
        }

        if(false){
            return ResponseEntity.status(400).build(); // Se os dados foram enviados incorretamente;
        }

        final Tipo tipoSalvo = this.repository.save(tipo);
        return ResponseEntity.status(201).body(tipoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Tipo>> listar() {
        List<Tipo> tipos = this.repository.findAll();

        if (tipos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption) caixa
        Optional<Tipo> tipoOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre
        // ou 404 caso contrário.
        return ResponseEntity.of(tipoOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> atualizar(@PathVariable int id, @RequestBody Tipo tipoAtualizado) {
        if (repository.existsById(id)) {
            tipoAtualizado.setId(id);
            Tipo registrado = this.repository.save(tipoAtualizado);
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
