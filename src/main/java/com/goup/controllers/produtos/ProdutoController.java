package com.goup.controllers.produtos;

import com.goup.dtos.estoque.produtos.ProdutoMapper;
import com.goup.dtos.estoque.produtos.ProdutoReq;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.repositories.produtos.*;
import com.goup.multiple_pk.ProdutoPK;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private CorRepository corRepository;
    @Autowired
    private ModeloRepository modeloRepository;


    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid ProdutoReq produto) {
        Optional<Cor> cor = corRepository.findById(produto.idCor());
        Optional<Modelo> modelo = modeloRepository.findById(produto.idModelo());
        if(cor.isEmpty() || modelo.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        boolean produtoCorModeloExiste = this.repository.findAllByCorAndModelo(cor.get(), modelo.get()).size() > 0;

        if(produtoCorModeloExiste){
            return ResponseEntity.status(409).build();
        }

        final Produto produtoSalvo = this.repository.save(ProdutoMapper.reqToEntity(produto, cor.get(), modelo.get()));
        return ResponseEntity.status(201).body(produtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = this.repository.findAll();
        if (produtos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(produtos);
    }

    @GetMapping("/especifico")
    public ResponseEntity<Produto> buscarPorId(@RequestParam int id, @RequestParam int idCor, @RequestParam int idModelo) {
        Optional<Cor> cor = corRepository.findById(idCor);
        Optional<Modelo> modelo = modeloRepository.findById(idModelo);
        if(cor.isPresent() || modelo.isPresent()){
            ProdutoPK pk = new ProdutoPK(id, cor.get(), modelo.get());
            Optional<Produto> produtoOpt = repository.findById(pk);
            if(produtoOpt.isPresent()){
                return ResponseEntity.status(200).body(produtoOpt.get());
            }
        }
        return ResponseEntity.status(404).build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Modelo> atualizar(@PathVariable int id, @RequestBody @Valid Modelo modeloAtualizado) {
//        if (repository.existsById(id)) {
//            modeloAtualizado.setId(id);
//            Modelo registrado = this.repository.save(modeloAtualizado);
//            return ResponseEntity.status(200).body(registrado);
//        }
//        return ResponseEntity.status(404).build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> remover(@PathVariable int id) {
//        if (repository.existsById(id)) {
//            this.repository.deleteById(id);
//            return ResponseEntity.status(204).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

}
