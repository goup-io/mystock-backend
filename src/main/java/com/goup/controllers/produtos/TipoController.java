package com.goup.controllers.produtos;

import com.goup.entities.estoque.produtos.modelos.Tipo;
import com.goup.services.produtos.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${mystock.api.prefix}/tipos")
public class TipoController {
    @Autowired
    private TipoService service;
    @GetMapping
    public ResponseEntity<List<Tipo>> listar() {
        List<Tipo> tipos = this.service.listar();
        return tipos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tipo> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }
}
