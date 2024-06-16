package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.modelos.tipo.TipoMapper;
import com.goup.dtos.estoque.produtos.modelos.tipo.TipoReq;
import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.repositories.produtos.TipoRepository;
import com.goup.services.produtos.TipoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// CRUD funcionando
@RestController
@RequestMapping("/tipos")
public class TipoController {
    @Autowired
    private TipoService service;

    @PostMapping
    public ResponseEntity<Tipo> cadastrar(@RequestBody @Valid TipoReq tipo) {
        return ResponseEntity.status(201).body(service.cadastrar(tipo));
    }

    @GetMapping
    public ResponseEntity<List<Tipo>> listar() {
        List<Tipo> tipos = this.service.listar();
        return tipos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> atualizar(@PathVariable int id, @RequestBody @Valid TipoReq tipoAtualizado) {
        return ResponseEntity.status(200).body(service.atualizar(id, tipoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        service.remover(id);
        return ResponseEntity.status(204).build();
    }
}
