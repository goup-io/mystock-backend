package com.goup.controllers.produtos;
import com.goup.dtos.estoque.tamanho.TamanhoMapper;
import com.goup.dtos.estoque.tamanho.TamanhoReq;
import com.goup.entities.estoque.Tamanho;
import com.goup.repositories.produtos.TamanhoRepository;
import com.goup.services.produtos.TamanhoService;
import jakarta.validation.Valid;
import org.hibernate.persister.entity.SingleTableEntityPersister;
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
    private TamanhoService service;
    @PostMapping
    public ResponseEntity<Tamanho> cadastrar(@RequestBody @Valid TamanhoReq tamanho) {
        return ResponseEntity.status(201).body(service.cadastrar(tamanho));
    }

    @GetMapping
    public ResponseEntity<List<Tamanho>> listar() {
        List<Tamanho> tamanhos = this.service.listar();
        if (tamanhos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(tamanhos);
    }

    @GetMapping("/{tamanho}")
    public ResponseEntity<Tamanho> buscarPorNumeracao(@PathVariable Integer tamanho) {
        return ResponseEntity.status(200).body(this.service.buscarPorNumeracao(tamanho));
    }

    @DeleteMapping("/{tamanho}")
    public ResponseEntity<Void> deletar(@PathVariable Integer tamanho){
        service.deletar(tamanho);
        return ResponseEntity.status(204).build();
    }
}
