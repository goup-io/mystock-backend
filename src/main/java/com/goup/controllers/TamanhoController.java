package com.goup.controllers;
import com.goup.dtos.tamanho.TamanhoMapper;
import com.goup.dtos.tamanho.TamanhoReq;
import com.goup.entities.produtos.Tamanho;
import com.goup.repositories.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// CRUD funcionando
@RestController
@RequestMapping("/tamanhos")
public class TamanhoController {
    @Autowired
    private final TamanhoRepository repository;

    public TamanhoController(TamanhoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Tamanho> cadastrar(@RequestBody TamanhoReq tamanho) {
        final Tamanho tamanhoSalvo = this.repository.save(TamanhoMapper.reqToEntity(tamanho));
        return ResponseEntity.status(201).body(tamanhoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Tamanho>> listar() {
        List<Tamanho> tamanhos = this.repository.findAll();

        if (tamanhos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tamanhos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tamanho> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption) caixa
        Optional<Tamanho> tamanhoOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre ou 404 caso contrário.
        return ResponseEntity.of(tamanhoOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tamanho> atualizar(@PathVariable int id, @RequestBody TamanhoReq tamanhoAtualizado) {
        Optional<Tamanho> tamanhoOpt = repository.findById(id);
        if (tamanhoOpt.isPresent()) {
            tamanhoOpt.get().setNumero(tamanhoAtualizado.numero());
            this.repository.save(tamanhoOpt.get());
            return ResponseEntity.status(200).body(tamanhoOpt.get());
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
