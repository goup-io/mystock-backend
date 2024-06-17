package com.goup.controllers.relatorios;

import com.goup.dtos.relatorios.ResumoRes;
import com.goup.entities.vendas.TipoVenda;
import com.goup.services.relatorios.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {
    @Autowired
    private RelatorioService service;


    @GetMapping("/secao-resumo")
    public ResponseEntity<ResumoRes> buscarResumoVendas(
            @RequestParam(required = true) Integer qtdDias
    ){
        return ResponseEntity.status(200).body(service.buscarResumoVendas(qtdDias));
    }
}
