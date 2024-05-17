package com.goup.controllers.vendas;

import com.goup.dtos.vendas.tipoVenda.TipoVendaDescontoReq;
import com.goup.dtos.vendas.tipoVenda.TipoVendaMapper;
import com.goup.dtos.vendas.tipoVenda.TipoVendaReq;
import com.goup.dtos.vendas.tipoVenda.TipoVendaRes;
import com.goup.services.vendas.TipoVendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiposVenda")
public class TipoVendaController {

    @Autowired
    private TipoVendaService tipoVendaService;

    @GetMapping
    public ResponseEntity<List<TipoVendaRes>> listar(){
        return ResponseEntity.status(200).body(tipoVendaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoVendaRes> buscarTipoVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(TipoVendaMapper.entityToRes(tipoVendaService.buscarTipoVenda(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoVendaRes> atualizar(@Valid @RequestBody TipoVendaReq tipoVendaReq, @PathVariable Integer id){
        return ResponseEntity.status(200).body(tipoVendaService.atualizar(tipoVendaReq, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TipoVendaRes> atualizarDesconto(@Valid @RequestBody TipoVendaDescontoReq descontoReq, @PathVariable Integer id){
        return ResponseEntity.status(200).body(tipoVendaService.atualizarDesconto(descontoReq, id));
    }
}
