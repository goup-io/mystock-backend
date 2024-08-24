package com.goup.controllers.vendas;

import com.goup.dtos.vendas.tipoPagamento.TipoPagamentoRes;
import com.goup.services.vendas.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${mystock.api.prefix}/tiposPagamento")
public class TipoPagamentoController {
    @Autowired
    private TipoPagamentoService service;

    @GetMapping
    public ResponseEntity<List<TipoPagamentoRes>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPagamentoRes> buscarTipoPagamento(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.buscarTipoPagamento(id));
    }
}
