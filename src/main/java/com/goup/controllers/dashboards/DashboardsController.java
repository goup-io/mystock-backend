package com.goup.controllers.dashboards;

import com.goup.dtos.dashboards.dashboardGeral.FaturamentoLojaRes;
import com.goup.dtos.dashboards.dashboardGeral.KpisRes;
import com.goup.services.dashboards.DashboardGeralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboards")
public class DashboardsController {
    @Autowired
    private DashboardGeralService dashboardGeralService;

    @GetMapping("/dashboard-geral/kpis")
    public KpisRes buscarKpis(){
        return dashboardGeralService.dashGeralBuscarDadosKpi();
    }

    @GetMapping("/dashboard-geral/faturamento-por-loja")
    public Object[][] buscarFaturamentoPorLoja(){
        return dashboardGeralService.dashGeralBuscarFaturamentoPorLoja();
    }

    @GetMapping("/dashboard-geral/faturamento-por-loja/mes-atual")
    public Object[][] buscarFaturamentoPorLojaMes() {return dashboardGeralService.dashGeralBuscarFaturamentoPorLojaMes();}


}
