package com.goup.services.dashboards;

import com.goup.dtos.dashboards.dashboardFuncionario.KpisFuncionarioRes;
import com.goup.dtos.dashboards.dashboardFuncionario.TotaisItensVendidosRes;
import com.goup.dtos.dashboards.dashboardGeral.*;
import com.goup.entities.estoque.ETP;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.historicos.TransferenciaRepository;
import com.goup.repositories.lojas.LojaRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.vendas.PagamentoRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import com.goup.repositories.vendas.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
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
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VendaRepository vendaRepository;

    public KpisRes dashGeralBuscarDadosKpi(){
        Double faturamentoMes = pagamentoRepository.sumValorTotalByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()) == null ? 0.0 : pagamentoRepository.sumValorTotalByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        Double faturamentoDia = pagamentoRepository.sumValorTotalByDayMonthAndYear(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear()) == null ? 0.0 : pagamentoRepository.sumValorTotalByDayMonthAndYear(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        PageRequest pegarMaisVendido = PageRequest.of(0, 1);
        Page<ETP> topETP = produtoVendaRepository.findTopETPByMonthAndYear(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), pegarMaisVendido);
        Integer produtoEmEstoque = etpRepository.sumETP_Quantidade();
        return getKpisRes(faturamentoMes, faturamentoDia, topETP, produtoEmEstoque);
    }

    public Object[][] dashGeralBuscarFaturamentoPorLoja() {
        List<Loja> lojas = lojaRepository.findAll();
        if (lojas.isEmpty()) {
            throw new BuscaRetornaVazioException("Nenhuma loja encontrada!");
        }

        // Alteração para 13 colunas (1 para nome da loja + 12 para faturamentos)
        Object[][] faturamentoPorLoja = new Object[lojas.size()][13];
        for (int i = 0; i < lojas.size(); i++) {
            LocalDateTime dataInicial = LocalDateTime.now(); // Mês atual (novembro de 2024)
            Integer mesAtual = dataInicial.getMonthValue(); // Mês atual
            Integer anoAtual = dataInicial.getYear(); // Ano atual
            int contador = 0;

            faturamentoPorLoja[i][0] = lojas.get(i).getNome(); // Nome da loja

            for (int j = mesAtual; contador < 12; j--) {
                if (j < 1) {
                    j = 12;
                    anoAtual -= 1;
                }

                Double valorTotal = pagamentoRepository.sumPagamentosByLojaAndMonthAndYear(j, anoAtual, lojas.get(i).getId());

                if (valorTotal == null) {
                    valorTotal = 0.0; // Caso não haja valor, considera 0
                }

                BigDecimal valorArredondado = new BigDecimal(valorTotal).setScale(2, RoundingMode.HALF_UP);

                faturamentoPorLoja[i][12 - contador] = valorArredondado.doubleValue(); // Inverter a posição

                contador++;
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

    public List<FluxoEstoqueRes> dashGeralBuscarFluxoEstoques(){
            List<Loja> lojas = lojaRepository.findAll();
            List<FluxoEstoqueRes> fluxoEstoqueResList = new ArrayList<>();

            for (Loja loja : lojas) {
                String nomeLoja = loja.getNome();

                Integer tempQtdAtual = etpRepository.sumETP_QuantidadeByLoja(loja);
                Integer qtdAtual = tempQtdAtual == null ? 0 : tempQtdAtual;
                Integer tempQtdVendida = produtoVendaRepository.sumQuantidadeVendidaByLojaAndMesAndAno(loja, LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
                Integer qtdVendida = tempQtdVendida == null ? 0 : tempQtdVendida;
                Integer tempQtdTransferida = transferenciaRepository.sumQuantidadeTransferidaByLojaAndMonthAndAno(loja, LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
                Integer qtdTransferida = tempQtdTransferida == null ? 0 : tempQtdTransferida;

                FluxoEstoqueRes fluxoEstoqueRes = new FluxoEstoqueRes(nomeLoja, qtdAtual, qtdVendida, qtdTransferida);
                fluxoEstoqueResList.add(fluxoEstoqueRes);
            }

            return fluxoEstoqueResList;
    }

    public KpisRes dashboardLojaBuscarDadosKpi(Integer idLoja){
        Loja loja = lojaRepository.findById(idLoja).orElseThrow(() -> new RegistroNaoEncontradoException("Loja não encontrada!"));
        Double faturamentoMes = pagamentoRepository.sumValorTotalByMonthAndYearAndLoja(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), idLoja);
        Double faturamentoDia = pagamentoRepository.sumValorTotalByDayMonthAndYearAndLoja(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), idLoja);
        PageRequest pegarMaisVendido = PageRequest.of(0, 1);
        Page<ETP> topETP = produtoVendaRepository.findTopETPByMonthAndYearAndLoja(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), pegarMaisVendido, idLoja);
        Integer estoque = etpRepository.sumETP_QuantidadeByLoja(loja);
        return getKpisRes(faturamentoMes, faturamentoDia, topETP, estoque);
    }

    private KpisRes getKpisRes(Double faturamentoMes, Double faturamentoDia, Page<ETP> topETP, Integer produtosEmEstoque) {
        ETP etpMaisVendido = topETP.isEmpty() ? new ETP(): topETP.getContent().get(0);
        String modeloMaisVendido = etpMaisVendido.getId() == 0 ? "Nenhuma venda realizada" : etpMaisVendido.getProduto().getModelo().getNome();
        String produtoMaisVendido = etpMaisVendido.getId() == 0 ? "Nenhuma venda realizada" :  etpMaisVendido.getProduto().getNome();
        Integer estoque = produtosEmEstoque == null ? 0 : produtosEmEstoque;
        return new KpisRes(faturamentoMes != null ? faturamentoMes : 0.0 , faturamentoDia != null ? faturamentoDia : 0.0, modeloMaisVendido, produtoMaisVendido, estoque);
    }

    public Object[][] dashboardLojaBuscarFaturamentoPorLoja(Integer idLoja) {
        Loja loja = lojaRepository.findById(idLoja).orElseThrow(() -> new RegistroNaoEncontradoException("Loja não encontrada!"));

        Object[][] faturamentoPorLoja = new Object[1][13];
        LocalDateTime dataInicial = LocalDateTime.now().minusMonths(12);
        Integer mesInicial = dataInicial.getMonthValue();
        Integer anoPesquisar = dataInicial.getYear();
        int contador = 0;
        faturamentoPorLoja[0][0] = loja.getNome();

        for (int j = mesInicial; contador < 12; j++) {
            Double valorTotal = pagamentoRepository.sumPagamentosByLojaAndMonthAndYear((j + 1), anoPesquisar, loja.getId());

            if (valorTotal == null) {
                valorTotal = 0.0;
            }

            BigDecimal valorBigDecimal = new BigDecimal(valorTotal).setScale(1, RoundingMode.HALF_UP);

            faturamentoPorLoja[0][contador + 1] = valorBigDecimal.doubleValue();

            if (j == 12) {
                j = 0;
                anoPesquisar += 1;
            }
            contador++;
        }
        return faturamentoPorLoja;
    }

    public Object[][] dashboardLojaBuscarFaturamentoPorLojaMes(Integer idLoja) {
        Loja loja = lojaRepository.findById(idLoja).orElseThrow(() -> new RegistroNaoEncontradoException("Loja não encontrada!"));

        Object[][] faturamentoPorLoja = new Object[1][32];
        for (int i = 0; i < 1; i++){
            LocalDateTime dataInicial = LocalDateTime.now();
            Integer mesInicial = dataInicial.getMonthValue();
            Integer anoPesquisar = dataInicial.getYear();
            YearMonth yearMonth = YearMonth.of(anoPesquisar, mesInicial);
            int contador = yearMonth.lengthOfMonth();
            faturamentoPorLoja[i][0] = loja.getNome();
            for (int j = 1; contador > 0; j++){
                Double valorTotal = pagamentoRepository.sumPagamentosByLojaAndMonthAndYearAndDay(j, mesInicial, anoPesquisar, loja.getId());

                if (valorTotal == null){
                    valorTotal = 0.0;
                }

                faturamentoPorLoja[i][j] = valorTotal;
                contador--;
            }
        }
        return faturamentoPorLoja;
    }

    public FluxoEstoqueRes dashboardLojaBuscarFluxoEstoques(Integer idLoja) {
        Loja loja = lojaRepository.findById(idLoja).orElseThrow(() -> new RegistroNaoEncontradoException("Loja não encontrada!"));

        String nomeLoja = loja.getNome();
        Integer tempQtdAtual = etpRepository.sumETP_QuantidadeByLoja(loja);
        Integer qtdAtual = tempQtdAtual == null ? 0 : tempQtdAtual;
        Integer tempQtdVendida = produtoVendaRepository.sumQuantidadeVendidaByLojaAndMesAndAno(loja, LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        Integer qtdVendida = tempQtdVendida == null ? 0 : tempQtdVendida;
        Integer tempQtdTransferida = transferenciaRepository.sumQuantidadeTransferidaByLojaAndMonthAndAno(loja, LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        Integer qtdTransferida = tempQtdTransferida == null ? 0 : tempQtdTransferida;

        return new FluxoEstoqueRes(nomeLoja, qtdAtual, qtdVendida, qtdTransferida);
    }

    public List<ModeloEValorRes> dashboardLojaBuscarModelosMaisVendidos(Integer idLoja) {
        Loja loja = lojaRepository.findById(idLoja).orElseThrow(() -> new RegistroNaoEncontradoException("Loja não encontrada!"));
        return pagamentoRepository.findTop10ModelosByLojaIdMonthAndYear(loja.getId(),LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
    }

    public List<RankingFuncionariosRes> dashboardLojaBuscarRankingFuncionarios(Integer idLoja) {
        Loja loja = lojaRepository.findById(idLoja).orElseThrow(() -> new RegistroNaoEncontradoException("Loja não encontrada!"));
        return usuarioRepository.sumValorVendidoByUsuario(idLoja, LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
    }

    public KpisFuncionarioRes dashboardFuncionarioBuscarKpi(Integer idFuncionario){
        Usuario usuario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new RegistroNaoEncontradoException("Funcionário não encontrado!"));
        Double faturamentoMes = pagamentoRepository.sumValorTotalByMonthAndYearAndUsuario(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), usuario.getId());
        Double faturamentoDia = pagamentoRepository.sumValorTotalByDayMonthAndYearAndUsuario(LocalDateTime.now().getDayOfMonth(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), usuario.getId());
        Integer qtdVendas = vendaRepository.countVendasByUsuario(usuario.getId(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        Integer qtdProdutosVendidos = produtoVendaRepository.sumProdutoVendaByUsuarioIdAndMesAndAno(usuario.getId(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
        PageRequest pegarMaisVendido = PageRequest.of(0, 1);
        Page<ETP> topETP = produtoVendaRepository.findTopETPByMonthAndYearAndUsuarioId(LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear(), pegarMaisVendido, usuario.getId());
        return
                new KpisFuncionarioRes(
                        faturamentoMes == null ? 0.0 : faturamentoMes,
                        faturamentoDia == null ? 0.0 : faturamentoDia,
                        qtdVendas == null ? 0 : qtdVendas,
                        qtdProdutosVendidos == null ? 0 : qtdProdutosVendidos,
                        topETP.isEmpty() ? "Nenhuma venda realizada" : topETP.getContent().get(0).getProduto().getNome());
    }

    public List<Double> dashboardFuncionarioBuscarFaturamentoPorFuncionario(Integer idFuncionario) {
        Usuario usuario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrada!"));
        List<Double> faturamentoFuncionario = new ArrayList<>();
        LocalDateTime dataInicial = LocalDateTime.now().minusMonths(12);
        Integer mesInicial = dataInicial.getMonthValue();
        Integer anoPesquisar = dataInicial.getYear();
        int contador = 0;
        for (int j = mesInicial; contador < 12; j++){
            Double valorTotal = pagamentoRepository.sumPagamentosByUsuarioAndMonthAndYear((j + 1), anoPesquisar, usuario.getId());

            if (valorTotal == null){
                valorTotal = 0.0;
            }

            faturamentoFuncionario.add(valorTotal);

            if (j == 12){
                j = 0;
                anoPesquisar += 1;
            }
            contador++;
        }
        return faturamentoFuncionario;
    }

    public List<Double> dashboardFuncionarioBuscarFaturamentoPorFuncionarioMes(Integer idFuncionario) {
        Usuario usuario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrada!"));

        List<Double> faturamentoFuncionario = new ArrayList<>();
        for (int i = 0; i < 1; i++){
            LocalDateTime dataInicial = LocalDateTime.now();
            Integer mesInicial = dataInicial.getMonthValue();
            Integer anoPesquisar = dataInicial.getYear();
            YearMonth yearMonth = YearMonth.of(anoPesquisar, mesInicial);
            int contador = yearMonth.lengthOfMonth();
            for (int j = 1; j <= contador; j++){
                Double valorTotal = pagamentoRepository.sumPagamentosByFuncionarioAndMonthAndYearAndDay(j, mesInicial, anoPesquisar, usuario.getId());

                if (valorTotal == null){
                    valorTotal = 0.0;
                }

                faturamentoFuncionario.add(valorTotal);
            }
        }
        return faturamentoFuncionario;
    }

    public List<ModeloEValorRes> dashboardFuncionarioBuscarModelosMaisVendidos(Integer idFuncionario) {
        Usuario usuario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrada!"));
        return pagamentoRepository.findTop10ModelosByUsuarioIdMonthAndYear(usuario.getId(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getYear());
    }

    public List<TotaisItensVendidosRes> dashboardFuncionarioBuscarTotaisItensVendidos(Integer idFuncionario) {
        Usuario usuario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrada!"));
        List<TotaisItensVendidosRes> faturamentoPorLoja = new ArrayList<>();
        LocalDateTime dataInicial = LocalDateTime.now().minusMonths(12);
        Integer mesInicial = dataInicial.getMonthValue() + 1;
        Integer anoPesquisar = dataInicial.getYear();
        int contador = 1;
        for (int j = mesInicial; contador < 13; j++){
            if (j == 13){
                j = 1;
                anoPesquisar += 1;
            }

            Integer qtdTotalVendidos = produtoVendaRepository.sumProdutoVendaByUsuarioIdAndMesAndAno(usuario.getId(), j, LocalDateTime.now().getYear());
            Integer qtdTotalVendidosPromocao = produtoVendaRepository.sumProdutoVendaPromocaoByUsuarioIdAndMesAndAno(j, anoPesquisar, usuario.getId());

            faturamentoPorLoja.add(new TotaisItensVendidosRes(qtdTotalVendidos == null ? 0 : qtdTotalVendidos, qtdTotalVendidosPromocao == null ? 0 : qtdTotalVendidosPromocao));

            contador++;
        }
        return faturamentoPorLoja;
    }
    public List<TotaisItensVendidosRes> dashboardFuncionarioBuscarTotaisItensVendidosMes(Integer idFuncionario) {
        Usuario usuario = usuarioRepository.findById(idFuncionario).orElseThrow(() -> new RegistroNaoEncontradoException("usuario não encontrada!"));

        List<TotaisItensVendidosRes> totaisItensVendidosResList = new ArrayList<>();
        LocalDateTime dataInicial = LocalDateTime.now();
        Integer mesInicial = dataInicial.getMonthValue();
        Integer anoPesquisar = dataInicial.getYear();
        YearMonth yearMonth = YearMonth.of(anoPesquisar, mesInicial);

        int totalDiasMes = yearMonth.lengthOfMonth();

        for (int dia = 1; dia <= totalDiasMes; dia++){
            Integer qtdTotalVendidos = produtoVendaRepository.sumProdutoVendaByUsuarioIdAndMesAndAnoAndDay(usuario.getId(), mesInicial, anoPesquisar, dia);
            Integer qtdTotalVendidosPromocao = produtoVendaRepository.sumProdutoVendaPromocaoByUsuarioIdAndMesAndAnoAndDay(mesInicial, anoPesquisar, usuario.getId(), dia);

            System.out.println("dia:" + dia + "mes"  + mesInicial + "ano: " + anoPesquisar + " valor: " + qtdTotalVendidos + "promocao: " + qtdTotalVendidosPromocao);
            totaisItensVendidosResList.add(
                    new TotaisItensVendidosRes(
                            qtdTotalVendidos == null ? 0 : qtdTotalVendidos,
                            qtdTotalVendidosPromocao == null ? 0 : qtdTotalVendidosPromocao
                    )
            );
        }

        return totaisItensVendidosResList;
    }
}
