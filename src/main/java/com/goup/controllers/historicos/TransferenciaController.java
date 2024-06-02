package com.goup.controllers.historicos;

import com.goup.dtos.historico.transferencia.TransferenciaReq;
import com.goup.dtos.historico.transferencia.TransferenciaReqAprovar;
import com.goup.dtos.historico.transferencia.TransferenciaReqRejeitar;
import com.goup.dtos.historico.transferencia.TransferenciaRes;
import com.goup.entities.historicos.StatusTransferencia;
import com.goup.services.historicos.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    @Autowired
    TransferenciaService service;
    @PostMapping
    public ResponseEntity<TransferenciaRes> cadastrar(@Valid @RequestBody TransferenciaReq transf){
        TransferenciaRes transfCadastrado = service.cadastrar(transf);
        return ResponseEntity.status(201).body(transfCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaRes>> listar(){
        List<TransferenciaRes> lista = service.listar();
        if(lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<TransferenciaRes>> listarPorFiltro(
            @RequestParam(required = false) LocalDateTime dataInicio,
            @RequestParam(required = false) LocalDateTime dataFim,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer tamanho,
            @RequestParam(required = false) StatusTransferencia.Status status
    ){
        List<TransferenciaRes> lista = service.listarPorFiltro(dataInicio, dataFim, modelo, cor, tamanho, status);
        if(lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping("/{id}/aprovar")
    public ResponseEntity<TransferenciaRes> aprovar(@PathVariable int id, @Valid @RequestBody TransferenciaReqAprovar transf){
        TransferenciaRes aprovada = service.aprovar(id, transf);
        return ResponseEntity.status(200).body(aprovada);
    }
    @PostMapping("/{id}/rejeitar")
    public ResponseEntity<TransferenciaRes> rejeitar(@PathVariable int id, @Valid @RequestBody TransferenciaReqRejeitar transf){
        TransferenciaRes aprovada = service.rejeitar(id, transf);
        return ResponseEntity.status(200).body(aprovada);
    }
}
