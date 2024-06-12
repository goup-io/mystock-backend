package com.goup.services.dashboards;

import com.goup.dtos.dashboards.dashboardGeral.KpisRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.lojas.Loja;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.vendas.PagamentoRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardGeralService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private LojaRepository lojaRepository;

    public KpisRes dashGeralBuscarDadosKpi(){
        Double faturamentoMes = pagamentoRepository.sumValorTotalByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        Double faturamentoDia = pagamentoRepository.sumValorTotalByDayMonthAndYear(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        ETP etpMaisVendido = produtoVendaRepository.findTopETPByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()).orElseThrow(() -> new BuscaRetornaVazioException("Sem vendas realizadas"));
        String modeloMaisVendido = etpMaisVendido.getProduto().getModelo().getNome();
        String produtoMaisVendido = etpMaisVendido.getProduto().getNome();
        Integer produtosEmEstoque = etpRepository.countETPs();
        return new KpisRes(faturamentoMes, faturamentoDia, modeloMaisVendido, produtoMaisVendido, produtosEmEstoque);
    }

    public Object[][] dashGeralBuscarFaturamentoPorLoja(){
        List<Loja> lojas = lojaRepository.findAll();
        if (lojas.isEmpty()){
            throw new BuscaRetornaVazioException("Nenhuma loja encontrada!");
        }

        Object[][] faturamentoPorLoja = new Object[lojas.size()][13];
        for (int i = 0; i < lojas.size(); i++){
            faturamentoPorLoja[i][0] = lojas.get(i).getNome();
            for (int j = 1; j <= 12; j++){
                Double valorTotal = pagamentoRepository.sumValorTotalByMonthAndYearAndLoja(j, LocalDateTime.now().getYear(), lojas.get(i).getId());
                if (valorTotal == null){
                    valorTotal = 0.0;
                }
                faturamentoPorLoja[i][j] = valorTotal;
            }
        }
        return faturamentoPorLoja;
    }

}
