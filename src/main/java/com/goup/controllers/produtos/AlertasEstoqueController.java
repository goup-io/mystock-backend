package com.goup.controllers.produtos;

import com.goup.entities.estoque.AlertasEstoque;
import com.goup.services.produtos.AlertasEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alertas")
public class AlertasEstoqueController {
    @Autowired
    AlertasEstoqueService service;

    @GetMapping
    public ResponseEntity<List<AlertasEstoque>> listar(){
        List<AlertasEstoque> alertas = service.listar();
        return alertas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(alertas);
    }

    @GetMapping("/loja/{id_loja}")
    public ResponseEntity<List<AlertasEstoque>> listarPorLoja(@PathVariable Integer id_loja){
        List<AlertasEstoque> alertas = service.listarPorLoja(id_loja);
        return alertas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(alertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertasEstoque> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }
}
