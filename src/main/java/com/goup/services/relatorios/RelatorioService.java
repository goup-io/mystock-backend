package com.goup.services.relatorios;

import com.goup.dtos.relatorios.ResumoRes;
import com.goup.repositories.vendas.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RelatorioService {
    @Autowired
    private VendaRepository vendaRepository;

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

        System.out.println("PRECODEREVENDA: " + entrada);
        System.out.println("PRECOCUSTO: " + saida);
        System.out.println("LUCROOPERACIONALDASEMANA: " + lucroOperacional);


        Double entradaDiasAnterior;
        Double saidaDiasAnterior;
        Double lucroOperacionalDiasAnterior;
        Double porcentagemLucroDiasAnterior = 0.0;

        return new ResumoRes(entrada, saida, lucroOperacional, porcentagemLucro);
    }
}
