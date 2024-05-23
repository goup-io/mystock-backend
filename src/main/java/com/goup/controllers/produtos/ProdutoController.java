package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.ProdutoReq;
import com.goup.dtos.estoque.produtos.ProdutoReqEdit;
import com.goup.dtos.estoque.produtos.ProdutoRes;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.services.produtos.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoRes> cadastrar(@RequestBody @Valid ProdutoReq produto){
        ProdutoRes produtoCadastrado = service.cadastrar(produto);
        return ResponseEntity.status(201).body(produtoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoRes>> listar(){
        List<ProdutoRes> lista = service.listar();
        if(lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRes> buscarPorId(@PathVariable int id){
        ProdutoRes produto = service.buscarPorId(id);
        return ResponseEntity.status(200).body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoRes> atualizar(@PathVariable int id, @RequestBody @Valid ProdutoReqEdit produtoNovo){
        ProdutoRes produtoAtualizado = service.atualizar(id, produtoNovo);
        return ResponseEntity.status(200).body(produtoAtualizado);
    }

    /*
    Devido ao relacionamento entre Produto e ETP, a funcionalidade de deletar produto não vai existir.

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
     */
}
