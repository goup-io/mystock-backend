package com.goup.controllers.vendas;

import com.goup.dtos.vendas.venda.VendaReq;
import com.goup.dtos.vendas.venda.VendaRes;
import com.goup.services.vendas.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<List<VendaRes>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaRes> buscar(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VendaRes> criar(@Valid @RequestBody VendaReq reqDto) {
        return ResponseEntity.status(201).body(service.salvar(reqDto));
    }

    @PatchMapping("/{id}")
    ResponseEntity<VendaRes> cancelarVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.cancelarVenda(id));
    }


}
