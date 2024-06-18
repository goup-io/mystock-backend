package com.goup.controllers.relatorios;

import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.dtos.relatorios.ResumoRes;
import com.goup.services.relatorios.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {
    @Autowired
    private RelatorioService service;


    @GetMapping("/secao-resumo/resumo-geral")
    public ResponseEntity<ResumoRes> buscarResumoVendas(
            @RequestParam(required = true) Integer qtdDias
    ){
        return ResponseEntity.status(200).body(service.buscarResumoVendas(qtdDias));
    }

    @GetMapping("/secao-vendas/modelos-mais-vendidos")
    public ResponseEntity<List<ModeloEValorRes>> buscarSecaoVendas(
            @RequestParam(required = true) Integer qtdDias
    ){
        List<ModeloEValorRes> modeloEValorRes = service.buscarSecaoVendas(qtdDias);
        if (modeloEValorRes.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(modeloEValorRes);
    };
}
