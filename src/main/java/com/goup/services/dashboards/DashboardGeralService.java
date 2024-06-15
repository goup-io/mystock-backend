package com.goup.services.dashboards;

import com.goup.dtos.dashboards.dashboardGeral.KpisRes;
import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.lojas.Loja;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.vendas.PagamentoRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
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
        Double faturamentoMes = pagamentoRepository.sumValorTotalByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()) == null ? 0.0 : pagamentoRepository.sumValorTotalByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        Double faturamentoDia = pagamentoRepository.sumValorTotalByDayMonthAndYear(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()) == null ? 0.0 : pagamentoRepository.sumValorTotalByDayMonthAndYear(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        PageRequest pegarMaisVendido = PageRequest.of(0, 1);
        ETP topETP = produtoVendaRepository.findTopETPByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), pegarMaisVendido).getContent().get(0);
        ETP etpMaisVendido = Optional.ofNullable(topETP).orElse(new ETP());
        String modeloMaisVendido = etpMaisVendido.getId() == 0 ? "Nenhuma venda realizada" : etpMaisVendido.getProduto().getModelo().getNome();
        String produtoMaisVendido = etpMaisVendido.getId() == 0 ? "Nenhuma venda realizada" :  etpMaisVendido.getProduto().getNome();
        Integer produtosEmEstoque = etpRepository.sumETP_Quantidade();
        return new KpisRes(faturamentoMes, faturamentoDia, modeloMaisVendido, produtoMaisVendido, produtosEmEstoque);
    }

    public Object[][] dashGeralBuscarFaturamentoPorLoja(){
        List<Loja> lojas = lojaRepository.findAll();
        if (lojas.isEmpty()){
            throw new BuscaRetornaVazioException("Nenhuma loja encontrada!");
        }

        Object[][] faturamentoPorLoja = new Object[lojas.size()][13];
        for (int i = 0; i < lojas.size(); i++){
            LocalDateTime dataInicial = LocalDateTime.now().minusMonths(12);
            Integer mesInicial = dataInicial.getMonthValue();
            Integer anoPesquisar = dataInicial.getYear();
            int contador = 12;
            faturamentoPorLoja[i][0] = lojas.get(i).getNome();
            for (int j = mesInicial; contador >= 0; j++){
                if (j == 13){
                    j = 1;
                    anoPesquisar += 1;
                }
                Double valorTotal = pagamentoRepository.sumPagamentosByLojaAndMonthAndYear(j, anoPesquisar, lojas.get(i).getId());

                if (valorTotal == null){
                    valorTotal = 0.0;
                }

                faturamentoPorLoja[i][j] = valorTotal;
                contador--;
            }
        }
        return faturamentoPorLoja;
    }

    public Object[][] dashGeralBuscarFaturamentoPorLojaMes(){
        List<Loja> lojas = lojaRepository.findAll();
        if (lojas.isEmpty()){
            throw new BuscaRetornaVazioException("Nenhuma loja encontrada!");
        }

        Object[][] faturamentoPorLoja = new Object[lojas.size()][32];
        for (int i = 0; i < lojas.size(); i++){
            LocalDateTime dataInicial = LocalDateTime.now();
            Integer mesInicial = dataInicial.getMonthValue();
            Integer anoPesquisar = dataInicial.getYear();
            YearMonth yearMonth = YearMonth.of(anoPesquisar, mesInicial);
            int contador = yearMonth.lengthOfMonth();
            faturamentoPorLoja[i][0] = lojas.get(i).getNome();
            for (int j = 1; contador > 0; j++){
                Double valorTotal = pagamentoRepository.sumPagamentosByLojaAndMonthAndYearAndDay(j, mesInicial, anoPesquisar, lojas.get(i).getId());

                if (valorTotal == null){
                    valorTotal = 0.0;
                }

                faturamentoPorLoja[i][j] = valorTotal;
                contador--;
            }
        }
        return faturamentoPorLoja;
    }

    public List<ModeloEValorRes> dashGeralBuscarModelosMaisVendidos() {
        return pagamentoRepository.findTop10ModelosByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
    }
}
