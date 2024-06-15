package com.goup.controllers.dashboards;

import com.azure.core.annotation.Get;
import com.goup.dtos.dashboards.dashboardGeral.FaturamentoLojaRes;
import com.goup.dtos.dashboards.dashboardGeral.KpisRes;
import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.services.dashboards.DashboardGeralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboards")
public class DashboardsController {
    @Autowired
    private DashboardGeralService dashboardGeralService;

    @GetMapping("/dashboard-geral/kpis")
    public ResponseEntity<KpisRes> buscarKpis(){
        return ResponseEntity.status(200).body(dashboardGeralService.dashGeralBuscarDadosKpi());
    }

    @GetMapping("/dashboard-geral/faturamento-por-loja")
    public ResponseEntity<Object[][]> buscarFaturamentoPorLoja(){
        return ResponseEntity.status(200).body(dashboardGeralService.dashGeralBuscarFaturamentoPorLoja());
    }

    @GetMapping("/dashboard-geral/faturamento-por-loja/mes-atual")
    public ResponseEntity<Object[][]> buscarFaturamentoPorLojaMes() {
        return ResponseEntity.status(200).body(dashboardGeralService.dashGeralBuscarFaturamentoPorLojaMes());}

    @GetMapping("/dashboard-geral/modelos-mais-vendido")
    public ResponseEntity<List<ModeloEValorRes>> buscarModelosMaisVendido(){
        List<ModeloEValorRes>  modeloEValorRes = dashboardGeralService.dashGeralBuscarModelosMaisVendidos();
        return modeloEValorRes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(modeloEValorRes);
    }

}
