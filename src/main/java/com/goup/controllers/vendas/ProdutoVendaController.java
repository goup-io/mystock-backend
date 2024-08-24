package com.goup.controllers.vendas;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaReq;
import com.goup.dtos.vendas.produtoVenda.ProdutoVendaRes;
import com.goup.services.vendas.ProdutoVendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${mystock.api.prefix}/produto-vendas")
public class ProdutoVendaController {
    @Autowired
    private  ProdutoVendaService produtoVendaService;

    @PostMapping("/{idVenda}")
    public ResponseEntity<List<ProdutoVendaRes>> adicionarVendasAoProdutoVendas(@PathVariable Integer idVenda, @Valid @RequestBody List<ProdutoVendaReq> produtoVendas){
        return ResponseEntity.status(201).body(produtoVendaService.adicionarVendaNosProdutosVenda(produtoVendas, idVenda));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoVendaRes>> listar(){
        List<ProdutoVendaRes> produtos = produtoVendaService.listar();
        return produtos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoVendaRes> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(produtoVendaService.buscarPorId(id));
    }

    @GetMapping("/venda/{id_venda}")
    public ResponseEntity<List<ProdutoVendaRes>> listarProdutosDeVendaEspecifica(@PathVariable int id_venda){
        List<ProdutoVendaRes> produtos = produtoVendaService.listarProdutosDeVendaEspecifica(id_venda);
        return produtos.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(produtos);
    }
}
