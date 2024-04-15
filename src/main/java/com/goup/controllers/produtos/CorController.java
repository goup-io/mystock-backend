package com.goup.controllers.produtos;

import com.goup.dtos.cor.CorMapper;
import com.goup.dtos.cor.CorReq;
import com.goup.entities.produtos.Cor;
import com.goup.repositories.produtos.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// CRUD funcionando
@RestController
@RequestMapping("/cores")
public class CorController {
    @Autowired
    private final CorRepository repository;

    public CorController(CorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Cor> cadastrar(@RequestBody CorReq categoria) {
        final Cor corSalva = this.repository.save(CorMapper.reqToEntity(categoria));
        return ResponseEntity.status(201).body(corSalva);
    }

    @GetMapping
    public ResponseEntity<List<Cor>> listar() {
        List<Cor> cores = this.repository.findAll();

        if (cores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cor> buscarPorId(@PathVariable Integer id) {

        // Previnir NPE (NullPointerExecption)
        Optional<Cor> corOpt = repository.findById(id);

        // Retorna 200 com corpo caso encontre ou 404 caso contrário.
        return ResponseEntity.of(corOpt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cor> atualizar(@PathVariable int id, @RequestBody CorReq corAtualizada) {
        Optional<Cor> corOpt = repository.findById(id);
        if (corOpt.isPresent()) {
            corOpt.get().setNome(corAtualizada.nome());
            this.repository.save(corOpt.get());
            return ResponseEntity.status(200).body(corOpt.get());
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

