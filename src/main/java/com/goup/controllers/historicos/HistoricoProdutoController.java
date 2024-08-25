package com.goup.controllers.historicos;

import com.goup.dtos.historico.produto.HistoricoProdutoRes;
import com.goup.services.historicos.HistoricoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${mystock.api.prefix}/historico-produtos")
public class HistoricoProdutoController {
    @Autowired
    private HistoricoProdutoService historicoProdutoService;

    @GetMapping
    public ResponseEntity<List<HistoricoProdutoRes>> listar(){
        return ResponseEntity.status(200).body(historicoProdutoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoProdutoRes> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(historicoProdutoService.procurarPorId(id));
    }
    @GetMapping("/produto-venda/{id}")
    public ResponseEntity<List<HistoricoProdutoRes>> buscarPorProdutoVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(historicoProdutoService.pesquisarPorProdutoVenda(id));
    }


}
