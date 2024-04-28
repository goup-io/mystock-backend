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
import com.goup.utils.ListaGenerica;
import com.goup.utils.Utils;
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

    private Utils utils;

    @GetMapping
    public ResponseEntity<List<ETPTableRes>> listar(){
        List<ETP> etps = etpRepository.findAll();
        if (etps.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        ListaGenerica<ETP> eptsList = new ListaGenerica<>(etps.size());
        for (ETP etp : etps) {
            eptsList.adiciona(etp);
        }

        utils.ordenarNome(eptsList, 0, eptsList.getTamanho());

        return ResponseEntity.status(200).body(ETPMapper.toTableResponse(eptsList));
    }

    @PostMapping
    public ResponseEntity<ETPTableRes> salvar(@RequestBody ETPReq etp){
        Optional<Produto> produto = produtoRepository.findById(etp.idProduto());
        Optional<Tamanho> tamanho = tamanhoRepository.findByNumero(etp.tamanho());
        Optional<Loja> loja = lojaRepository.findById(etp.idLoja());

        if (produto.isEmpty() || tamanho.isEmpty() || loja.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        boolean etpExists = etpRepository.findByTamanhoAndLojaAndProduto(tamanho.get(), loja.get(), produto.get()).isPresent();
        if (etpExists) {
            return ResponseEntity.status(409).build();
        }

        ETP etpBuild = new ETP();
        etpBuild.setProduto(produto.get());
        etpBuild.setTamanho(tamanho.get());
        etpBuild.setLoja(loja.get());
        etpBuild.setQuantidade(0);

        ETP savedEtp = etpRepository.save(etpBuild);

        ETPTableRes responseDto = ETPMapper.toTableResponseEntity(savedEtp);
        return ResponseEntity.status(201).body(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        Optional<ETP> etp = etpRepository.findById(id);
        if (etp.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        etpRepository.delete(etp.get());
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}/{quantidade}")
    public ResponseEntity<ETPTableRes> incrementarEtp(@PathVariable Integer id, @PathVariable Integer quantidade){
        Optional<ETP> etp = etpRepository.findById(id);
        if (etp.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        etp.get().setQuantidade(etp.get().getQuantidade() + quantidade);
        ETP savedEtp = etpRepository.save(etp.get());
        ETPTableRes responseDto = ETPMapper.toTableResponseEntity(savedEtp);
        return ResponseEntity.status(200).body(responseDto);

    }
}
