package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.cor.CorMapper;
import com.goup.dtos.estoque.produtos.cor.CorReq;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.repositories.produtos.CorRepository;
import com.goup.services.produtos.CorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// CRUD funcionando
@RestController
@RequestMapping("${mystock.api.prefix}/cores")
public class CorController {
    @Autowired
    private CorService service;

    @GetMapping
    public ResponseEntity<List<Cor>> listar() {
        List<Cor> cores = this.service.listar();
        if (cores.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(cores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cor> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }
}

