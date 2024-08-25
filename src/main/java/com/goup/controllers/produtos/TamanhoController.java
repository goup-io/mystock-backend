package com.goup.controllers.produtos;
import com.goup.entities.estoque.Tamanho;
import com.goup.services.produtos.TamanhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${mystock.api.prefix}/tamanhos")
public class TamanhoController {
    @Autowired
    private TamanhoService service;

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
}
