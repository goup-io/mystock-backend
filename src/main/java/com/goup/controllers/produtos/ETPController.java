package com.goup.controllers.produtos;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.estoque.ETPReq;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.estoque.Tamanho;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.lojas.Loja;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.produtos.ProdutoRepository;
import com.goup.repositories.produtos.TamanhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etps")
public class ETPController {

    @Autowired
    private ETPRepository etpRepository;

    @Autowired
    private TamanhoRepository tamanhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public ResponseEntity<List<ETPTableRes>> listar(){
        List<ETP> etps = etpRepository.findAll();
        if (etps.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(ETPMapper.toTableResponse(etps));
    }


    @PostMapping
    public ResponseEntity<ETPTableRes> salvar(@RequestBody ETPReq etp){
        Optional<Tamanho> tamanho = tamanhoRepository.findByNumero(etp.tamanho());
        Optional<Produto> produto = produtoRepository.findById(etp.pkProduto());
        Optional<Loja> loja = lojaRepository.findById(etp.idLoja());
        if (tamanho.isEmpty() || produto.isEmpty() || loja.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        ETP etpBuilt = new ETP();
        etpBuilt.setTamanho(tamanho.get());
        etpBuilt.setProduto(produto.get());
        etpBuilt.setLoja(loja.get());
        etpBuilt.setQuantidade(0);

        return ResponseEntity.status(201).body(ETPMapper.toTableResponse(etpRepository.save(etpBuilt)));
    }
}
