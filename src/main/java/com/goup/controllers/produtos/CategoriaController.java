package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.modelos.categoria.CategoriaMapper;
import com.goup.dtos.estoque.produtos.modelos.categoria.CategoriaReq;
import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.repositories.produtos.CategoriaRepository;
import com.goup.services.produtos.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = this.service.listar();
        return categorias.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }
}
