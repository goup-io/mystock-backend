package com.goup.controllers.vendas;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaReq;
import com.goup.dtos.vendas.venda.VendaEProdutosReq;
import com.goup.dtos.vendas.venda.VendaReq;
import com.goup.dtos.vendas.venda.VendaRes;
import com.goup.dtos.vendas.venda.VendaResTable;
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
    public ResponseEntity<List<VendaResTable>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResTable> buscar(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<VendaRes> criar(@Valid @RequestBody VendaEProdutosReq reqDto) {
        return ResponseEntity.status(201).body(service.salvar(reqDto.vendaReq(), reqDto.produtosVendaReq()));
    }

    @PatchMapping("/cancelar/{id}")
    ResponseEntity<VendaRes> cancelarVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.cancelarVenda(id));
    }

    @PatchMapping("/finalizar/{id}")
    ResponseEntity<VendaRes> finalizarVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.finalizarVenda(id));
    }

    @GetMapping("/vendas-pendentes/{idLoja}")
    public ResponseEntity<List<VendaResTable>> listarVendasPendentes(@PathVariable Integer idLoja){
        return ResponseEntity.status(200).body(service.listarVendasPendentesPorLoja(idLoja));
    }
}
