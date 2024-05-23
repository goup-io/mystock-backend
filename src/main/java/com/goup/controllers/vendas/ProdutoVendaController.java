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
@RequestMapping("/produtos-venda")
public class ProdutoVendaController {
    @Autowired
    private  ProdutoVendaService produtoVendaService;

    @PostMapping("/{idVenda}")
    public ResponseEntity<List<ProdutoVendaRes>> adicionarVendasAoProdutoVendas(@PathVariable Integer idVenda, @Valid @RequestBody List<ProdutoVendaReq> produtoVendas){
        return ResponseEntity.status(201).body(produtoVendaService.adicionarVendaNosProdutosVenda(produtoVendas, idVenda));
    }
}
