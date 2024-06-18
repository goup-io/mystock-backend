package com.goup.controllers.relatorios;

import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.dtos.relatorios.FluxoEstoqueRes;
import com.goup.dtos.relatorios.ProdutoAcabandoRes;
import com.goup.dtos.relatorios.RankingFuncionariosVendas;
import com.goup.dtos.relatorios.ResumoRes;
import com.goup.services.relatorios.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/secao-funcionarios/ranking-vendas")
    public ResponseEntity<List<RankingFuncionariosVendas>> buscarRankingFuncionarios(
            @RequestParam(required = true) Integer qtdDias
    ){
        List<RankingFuncionariosVendas> ranking = service.dashboardLojaBuscarRankingFuncionarios(qtdDias);
        if(ranking.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(ranking);
    }

    @GetMapping("/secao-estoque/produtos-acabando")
    public ResponseEntity<List<ProdutoAcabandoRes>> buscarProdutosAcabando(){
        List<ProdutoAcabandoRes> produtosAcabando = service.buscarProdutosAcabando();
        if(produtosAcabando.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(produtosAcabando);
    }

    @GetMapping("/secao-estoque/fluxo-estoque")
    public ResponseEntity<FluxoEstoqueRes> buscarFluxoEstoque(
            @RequestParam(required = true) Integer qtdDias
    ){
        return ResponseEntity.status(200).body(service.buscarFluxoEstoque(qtdDias));
    }

}
