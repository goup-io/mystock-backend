package com.goup.controllers.produtos;

import com.goup.dtos.estoque.Notificacao;
import com.goup.entities.estoque.AlertasEstoque;
import com.goup.services.produtos.AlertasEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/filtro")
    public ResponseEntity<List<AlertasEstoque>> listarPorFiltro(
        @RequestParam(required = false) LocalDateTime dataInicio,
        @RequestParam(required = false) LocalDateTime dataFim,
        @RequestParam(required = false) Integer id_loja
    ){
        List<AlertasEstoque> alertas = service.listarPorFiltro(dataInicio, dataFim, id_loja);
        return alertas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(alertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertasEstoque> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }


    @GetMapping("/ultimo-aviso/{id_loja}")
    public ResponseEntity<Notificacao> listarUltimoAviso(@PathVariable Integer id_loja){
        return ResponseEntity.status(200).body(service.listarUltimoAviso(id_loja));
    }
}
