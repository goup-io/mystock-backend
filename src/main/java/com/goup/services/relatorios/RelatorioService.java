package com.goup.services.relatorios;

import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.dtos.relatorios.ResumoRes;
import com.goup.repositories.vendas.PagamentoRepository;
import com.goup.repositories.vendas.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public ResumoRes buscarResumoVendas(Integer qtdDias) {
        LocalDateTime dataMenosQtdDias = LocalDateTime.now().minusDays(qtdDias);

        Double entrada;
        Double saida;
        Double lucroOperacional;
        Double porcentagemLucro = 0.0;

        Double entradaTemp = vendaRepository.sumValorVendasPrecoRevenda(dataMenosQtdDias);
        Double saidaTemp = vendaRepository.sumValorVendasPrecoCusto(dataMenosQtdDias);

        entrada = entradaTemp == null ? 0.0 : entradaTemp;
        saida = saidaTemp == null ? 0.0 : saidaTemp;
        lucroOperacional = entrada - saida;

        Double entradaDiasAnterior;
        Double saidaDiasAnterior;
        Double lucroOperacionalDiasAnterior;

        Double entradaDiasAnteriorTemp = vendaRepository.sumValorVendasPrecoRevendaBeteweenDates(dataMenosQtdDias.minusDays(qtdDias), dataMenosQtdDias);
        Double saldoDiasAnteriorTemp = vendaRepository.sumValorVendasPrecoCustoBeteweenDates(dataMenosQtdDias.minusDays(qtdDias), dataMenosQtdDias);

        entradaDiasAnterior = entradaDiasAnteriorTemp == null ? 0.0 : entradaDiasAnteriorTemp;
        saidaDiasAnterior = saldoDiasAnteriorTemp == null ? 0.0 : saldoDiasAnteriorTemp;

        lucroOperacionalDiasAnterior = entradaDiasAnterior - saidaDiasAnterior;

        if (lucroOperacionalDiasAnterior != 0.0) {
            porcentagemLucro = ((lucroOperacional - lucroOperacionalDiasAnterior) / lucroOperacionalDiasAnterior) * 100;
        } else if (lucroOperacional > 0.0) {
            porcentagemLucro = 100.0; // Indica um aumento de 100%
        } else {
            porcentagemLucro = 0.0; // Indica que não houve lucro nem perda
        }

        return new ResumoRes(entrada, saida, lucroOperacional, porcentagemLucro);
    }

    public List<ModeloEValorRes> buscarSecaoVendas(Integer qtdDias) {
        LocalDateTime dataMenosQtdDias = LocalDateTime.now().minusDays(qtdDias);
        return pagamentoRepository.findTop10ModelosByPeriod(dataMenosQtdDias);
    }
}
