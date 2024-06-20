package com.goup.services.relatorios;

import com.goup.dtos.dashboards.dashboardGeral.ModeloEValorRes;
import com.goup.dtos.dashboards.dashboardGeral.RankingFuncionariosRes;
import com.goup.dtos.relatorios.FluxoEstoqueRes;
import com.goup.dtos.relatorios.ProdutoAcabandoRes;
import com.goup.dtos.relatorios.RankingFuncionariosVendas;
import com.goup.dtos.relatorios.ResumoRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.lojas.Loja;
import com.goup.repositories.historicos.TransferenciaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.vendas.PagamentoRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import com.goup.repositories.vendas.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;
    @Autowired
    private TransferenciaRepository transferenciaRepository;

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

    public List<RankingFuncionariosVendas> dashboardLojaBuscarRankingFuncionarios(Integer qtdDias) {
            List<RankingFuncionariosVendas> ranking = usuarioRepository.sumValorVendidoByUsuarioPeriod(LocalDateTime.now().minusDays(qtdDias));
            if (ranking.isEmpty()) {
                return new ArrayList<>();
            }
            return ranking;
        }

    public List<ProdutoAcabandoRes> buscarProdutosAcabando() {
        List<ETP> produtosAcabando = etpRepository.findAllByQuantidadeBeforeOrderByQuantidadeAsc(20);
        if (produtosAcabando.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProdutoAcabandoRes> produtosAcabandoRes = new ArrayList<>();
        for (ETP etp : produtosAcabando) {
            produtosAcabandoRes.add(
                    new ProdutoAcabandoRes(etp.getProduto().getNome(), etp.getQuantidade(), etp.getLoja().getNome()));
        }
        return produtosAcabandoRes;
    }

    public FluxoEstoqueRes buscarFluxoEstoque(Integer qtdDias) {
        LocalDateTime dataAtualMenosDias = LocalDateTime.now().minusDays(qtdDias);

            Integer tempQtdAtual = etpRepository.sumETP_Quantidade();
            Integer qtdAtual = tempQtdAtual == null ? 0 : tempQtdAtual;
            Integer tempQtdVendida = produtoVendaRepository.sumQuantidadeVendidaByPeriod(dataAtualMenosDias);
            Integer qtdVendida = tempQtdVendida == null ? 0 : tempQtdVendida;
            Integer tempQtdTransferida = transferenciaRepository.sumQuantidadeTransferidaByPeriod(dataAtualMenosDias);
            Integer qtdTransferida = tempQtdTransferida == null ? 0 : tempQtdTransferida;

        return new FluxoEstoqueRes(
                qtdAtual,
                qtdVendida,
                qtdTransferida
        );
    }
}
