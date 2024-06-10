package com.goup.controllers.produtos;

import com.goup.dtos.estoque.ETPEditModal;
import com.goup.dtos.estoque.ETPReq;
import com.goup.dtos.estoque.ETPTableRes;
import com.goup.dtos.estoque.ReqETPeQuantidade;
import com.goup.services.produtos.ETPService;
import jakarta.validation.Valid;
import org.hibernate.type.internal.UserTypeVersionJavaTypeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/etps")
public class ETPController {
    @Autowired
    ETPService service;

    @PostMapping
    public ResponseEntity<ETPTableRes> cadastrar(@RequestBody ETPReq etp){
        ETPTableRes etpCadastrado = service.cadastrar(etp);
        return ResponseEntity.status(201).body(etpCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ETPTableRes>> listar(){
        List<ETPTableRes> etps = service.listar();
        if (etps.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(etps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ETPTableRes> buscarPorId(@PathVariable Integer id){
        ETPTableRes etp = service.buscarPorId(id);
        return ResponseEntity.status(200).body(etp);
    }

    @GetMapping("/editar/{id}")
    public ResponseEntity<ETPEditModal> buscarDtoEditPorId(@PathVariable Integer id){
        ETPEditModal etp = service.buscarDtoEditPorId(id);
        return ResponseEntity.status(200).body(etp);
    }

    @GetMapping("/loja/{id_loja}")
    public ResponseEntity<List<ETPTableRes>> listarPorLoja(@PathVariable Integer id_loja){
        List<ETPTableRes> etps = service.listarPorLoja(id_loja);
        if(etps.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(etps);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<ETPTableRes>> listarPorFiltro(
        @RequestParam(required = false) String modelo,
        @RequestParam(required = false) String cor,
        @RequestParam(required = false) Integer tamanho,
        @RequestParam(required = false) Double precoMinimo,
        @RequestParam(required = false) Double precoMaximo,
        @RequestParam(required = false) Integer id_loja
    ){
        List<ETPTableRes> etps = service.listarPorFiltro(modelo, cor, tamanho, precoMinimo, precoMaximo, id_loja);
        if(etps.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(etps);
    }

    @PatchMapping("/{id}/{quantidade}")
    public ResponseEntity<ETPTableRes> alterarQuantidade(@PathVariable Integer id, @PathVariable Integer quantidade, @RequestParam Boolean soma){
        ETPTableRes etpAtualizado = service.alterarQuantidade(id, quantidade, soma);
        return ResponseEntity.status(200).body(etpAtualizado);
    }

    @PatchMapping("/adicionar-estoque/{idLoja}")
    public ResponseEntity<List<ETPTableRes>> alterarQuantidade(@Valid @RequestBody List<ReqETPeQuantidade> etpEQuantidade, @RequestParam Boolean soma, @PathVariable Integer idLoja){
        List<ETPTableRes> etpAtualizado = service.alterarQuantidade(etpEQuantidade, soma, idLoja);
        if (etpAtualizado.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(etpAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }


}
