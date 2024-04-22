package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.modelos.tipo.TipoMapper;
import com.goup.dtos.estoque.produtos.modelos.tipo.TipoReq;
import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.repositories.produtos.TipoRepository;
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
    private final TipoRepository repository;

    public TipoController(TipoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Tipo> cadastrar(@RequestBody @Valid TipoReq tipo) {
        final Tipo tipoSalvo = this.repository.save(TipoMapper.reqToEntity(tipo));
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
        // Previnir NPE (NullPointerExecption)
        Optional<Tipo> tipoOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre ou 404 caso contrário.
        return ResponseEntity.of(tipoOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tipo> atualizar(@PathVariable int id, @RequestBody @Valid TipoReq tipoAtualizado) {
        Optional<Tipo> tipoOpt = repository.findById(id);
        if (tipoOpt.isPresent()) {
            tipoOpt.get().setNome(tipoAtualizado.nome());
            this.repository.save(tipoOpt.get());
            return ResponseEntity.status(200).body(tipoOpt.get());
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
