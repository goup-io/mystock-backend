package com.goup.controllers.dashboards;

import com.goup.dtos.dashboards.dashboardFuncionario.TotaisItensVendidosRes;
import com.goup.dtos.dashboards.dashboardFuncionario.KpisFuncionarioRes;
import com.goup.dtos.dashboards.dashboardGeral.*;
import com.goup.services.dashboards.DashboardGeralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/dashboard-geral/fluxo-estoque")
    public ResponseEntity<List<FluxoEstoqueRes>> buscarFluxoTodasLojas(){
        List<FluxoEstoqueRes> fluxoEstoqueRes = dashboardGeralService.dashGeralBuscarFluxoEstoques();
        return fluxoEstoqueRes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(fluxoEstoqueRes);
    }

    @GetMapping("/dashboard-loja/{idLoja}/kpis")
    public ResponseEntity<KpisRes> buscarKpisLoja(@PathVariable Integer idLoja){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardLojaBuscarDadosKpi(idLoja));
    }

    @GetMapping("/dashboard-loja/{idLoja}/faturamento-por-loja")
    public ResponseEntity<Object[][]> buscarFaturamentoPorLoja(@PathVariable Integer idLoja){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardLojaBuscarFaturamentoPorLoja(idLoja));
    }

    @GetMapping("/dashboard-loja/{idLoja}/faturamento-por-loja/mes-atual")
    public ResponseEntity<Object[][]> buscarFaturamentoPorLojaMes(@PathVariable Integer idLoja) {
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardLojaBuscarFaturamentoPorLojaMes(idLoja));
    }

    @GetMapping("/dashboard-loja/{idLoja}/fluxo-estoque")
    public ResponseEntity<FluxoEstoqueRes> buscarFluxoLoja(@PathVariable Integer idLoja){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardLojaBuscarFluxoEstoques(idLoja));
    }

    @GetMapping("/dashboard-loja/{idLoja}/modelos-mais-vendido")
    public ResponseEntity<List<ModeloEValorRes>> buscarModelosMaisVendidoLoja(@PathVariable Integer idLoja){
        List<ModeloEValorRes>  modeloEValorRes = dashboardGeralService.dashboardLojaBuscarModelosMaisVendidos(idLoja);
        return modeloEValorRes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(modeloEValorRes);
    }

    @GetMapping("/dashboard-loja/{idLoja}/ranking-funcionarios")
    public ResponseEntity<List<RankingFuncionariosRes>> buscarRankingFuncionarios(@PathVariable Integer idLoja){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardLojaBuscarRankingFuncionarios(idLoja));
    }

    @GetMapping("/dashboard-funcionario/{idFuncionario}/kpis")
    public ResponseEntity<KpisFuncionarioRes> buscarKpisFuncionario(@PathVariable Integer idFuncionario){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardFuncionarioBuscarKpi(idFuncionario));
    }

    @GetMapping("/dashboard-funcionario/{idFuncionario}/faturamento")
    public ResponseEntity<List<Double>> buscarFaturamentoFuncionario(@PathVariable Integer idFuncionario){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardFuncionarioBuscarFaturamentoPorFuncionario(idFuncionario));
    }

    @GetMapping("/dashboard-funcionario/{idFuncionario}/faturamento-mes-atual")
    public ResponseEntity<Double> buscarFaturamentoFuncionarioMes(@PathVariable Integer idFuncionario){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardFuncionarioBuscarFaturamentoPorFuncionarioMes(idFuncionario));
    }

    @GetMapping("/dashboard-funcionario/{idFuncionario}/modelos-mais-vendido")
    public ResponseEntity<List<ModeloEValorRes>> buscarModelosMaisVendidoFuncionario(@PathVariable Integer idFuncionario){
        List<ModeloEValorRes>  modeloEValorRes = dashboardGeralService.dashboardFuncionarioBuscarModelosMaisVendidos(idFuncionario);
        return modeloEValorRes.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(modeloEValorRes);
    }

    @GetMapping("/dashboard-funcionario/{idFuncionario}/totais-de-itens-vendidos")
    public ResponseEntity<List<TotaisItensVendidosRes>> buscarTotaisItensVendidosFuncionario(@PathVariable Integer idFuncionario){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardFuncionarioBuscarTotaisItensVendidos(idFuncionario));
    }

    @GetMapping("/dashboard-funcionario/{idFuncionario}/totais-de-itens-vendidos/mes-atual")
    public ResponseEntity<TotaisItensVendidosRes> buscarTotaisItensVendidosFuncionarioMes(@PathVariable Integer idFuncionario){
        return ResponseEntity.status(200).body(dashboardGeralService.dashboardFuncionarioBuscarTotaisItensVendidosMes(idFuncionario));
    }

}
