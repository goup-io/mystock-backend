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
        ETP etpMaisVendido = produtoVendaRepository.findTopETPByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
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
        Object[][][] faturamentoPorLoja = new Object[lojas.size()][12][12];
        for (int i = 0; i < lojas.size(); i++){
            for (int j = 0; j < 12; j++){
                faturamentoPorLoja[i][j][0] = lojas.get(i).getNome();
                faturamentoPorLoja[i][j][1] = pagamentoRepository.sumValorTotalByMonthAndYear(j+1, LocalDateTime.now().getYear());
            }
        }
        return faturamentoPorLoja;
    }

}
