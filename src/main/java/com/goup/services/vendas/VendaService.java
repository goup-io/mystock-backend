package com.goup.services.vendas;

import com.goup.dtos.historico.produto.HistoricoProdutoReq;
import com.goup.dtos.historico.produto.HistoricoProdutoRes;
import com.goup.dtos.vendas.produtoVenda.*;
import com.goup.dtos.vendas.venda.*;
import com.goup.entities.estoque.AlertaInfos;
import com.goup.entities.estoque.AlertasEstoque;
import com.goup.entities.estoque.ETP;
import com.goup.entities.historicos.StatusHistoricoProduto;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.ProdutoVenda;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.OperacaoInvalidaException;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.AlertasEstoqueRepository;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.usuarios.UsuarioRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import com.goup.repositories.vendas.StatusVendaRepository;
import com.goup.repositories.vendas.TipoVendaRepository;
import com.goup.repositories.vendas.VendaRepository;
import com.goup.services.historicos.HistoricoProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import com.goup.services.produtos.AlertasEstoqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoVendaRepository tipoVendaRepository;
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private StatusVendaRepository statusVendaRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;
    @Autowired
    private HistoricoProdutoService historicoProdutoService;
    @Autowired
    private AlertasEstoqueService alertasEstoqueService;
    @Autowired
    private VendaRepository vendaRepository;


    public List<VendaResTable> listar(){
        List<Venda> vendas = repository.findAll();
        if (vendas.isEmpty()){
            throw new BuscaRetornaVazioException("Venda não encontrou algum resultado");
        }

        List<Integer> quantidadePorProdutoVenda = new ArrayList<>();
        for (int i = 0; i < vendas.size(); i++) {
            List<RetornoETPeQuantidade> itensDaVenda = produtoVendaRepository.findAllEtpsByVendaId(vendas.get(i).getId());
            Integer qtdTotal = 0;
            for (RetornoETPeQuantidade retornoETPeQuantidade : itensDaVenda) {
                qtdTotal += retornoETPeQuantidade.quantidade();
            }
            quantidadePorProdutoVenda.add(qtdTotal);
        }
        return VendaMapper.entityToResTableList(vendas, quantidadePorProdutoVenda);
    }

    public List<VendaResTable> listarPorFiltro(
        @Param("tipo_venda") Integer id_tipo_venda,
        @Param("id_vendedor") Integer id_vendedor,
        @Param("data_inicio") LocalDateTime dataHoraInicio,
        @Param("data_fim") LocalDateTime dataHoraFim,
        @Param("id_loja") Integer id_loja,
        @Param("status") StatusVenda.Status  status
    ){
        if (id_tipo_venda != null) {
            Optional<TipoVenda> tipoVenda = tipoVendaRepository.findById(id_tipo_venda);
            if (tipoVenda.isEmpty()) throw new RegistroNaoEncontradoException("TipoVenda não encontrada");
        }else if (status != null) {
            Optional<StatusVenda> statusVenda = statusVendaRepository.findByStatus(status);
            if (statusVenda.isEmpty()) throw new RegistroNaoEncontradoException("StatusVenda não encontrado");
        }

        List<Venda> vendas = repository.findAllByFiltros(id_tipo_venda, id_vendedor, dataHoraInicio, dataHoraFim, id_loja, status);


        List<Integer> quantidadePorProdutoVenda = new ArrayList<>();
        for (int i = 0; i < vendas.size(); i++) {
            List<RetornoETPeQuantidade> itensDaVenda = produtoVendaRepository.findAllEtpsByVendaId(vendas.get(i).getId());
            Integer qtdTotal = 0;
            for (RetornoETPeQuantidade retornoETPeQuantidade : itensDaVenda) {
                qtdTotal += retornoETPeQuantidade.quantidade();
            }
            quantidadePorProdutoVenda.add(qtdTotal);
        }
        return VendaMapper.entityToResTableList(vendas, quantidadePorProdutoVenda);
    }

    public VendaResTable buscarPorId(Integer id){
        Optional<Venda> venda = repository.findById(id);
        if (venda.isEmpty()){
            throw new  RegistroNaoEncontradoException("Venda não encontrou o ID informado");
        }
        Integer qtdTotal = 0;
        List<RetornoETPeQuantidade> itensDaVenda = produtoVendaRepository.findAllEtpsByVendaId(id);
        for (RetornoETPeQuantidade retornoETPeQuantidade : itensDaVenda) {
            qtdTotal += retornoETPeQuantidade.quantidade();
        }

        return VendaMapper.entityToResTable(venda.get(), qtdTotal);
    }

    public VendaRes salvar(@Valid VendaReq req, List<ProdutoVendaReq> retornoETPeQuantidades) {
        Usuario usuario = usuarioRepository.findByCodigoVenda(req.codigoVendedor())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuario não encontrado"));

        TipoVenda tipoVenda = tipoVendaRepository.findById(req.tipoVendaId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("TipoVenda não encontrado"));

        StatusVenda statusEmAndamento = statusVendaRepository.findByStatus(StatusVenda.Status.PENDENTE)
                .orElseThrow(() -> new RegistroNaoEncontradoException("StatusVenda não encontrado"));

        if (!isQuantidadeValida(retornoETPeQuantidades)) {
            throw new OperacaoInvalidaException("Quantidade de produtos insuficiente");
        }

        Venda venda = repository.save(VendaMapper.reqToEntity(req, usuario, tipoVenda, statusEmAndamento));

        double valorTotal = calcularEAdicionarProdutos(venda, retornoETPeQuantidades);

        venda.setValorTotal(valorTotal - venda.getDesconto());
        venda = repository.save(venda);

        // Dando baixa dos produtos no estoque
        alterarEtpBaseadoVenda(venda.getId(), false);

        return VendaMapper.entityToRes(venda);
    }

    private boolean isQuantidadeValida(List<ProdutoVendaReq> retornoETPeQuantidades) {
        for (ProdutoVendaReq produtoVendaReq : retornoETPeQuantidades) {
            ETP etp = etpRepository.findById(produtoVendaReq.etpId())
                    .orElseThrow(() -> new RegistroNaoEncontradoException("ETP não encontrado"));

            if (etp.getQuantidade() < produtoVendaReq.quantidade()) {
                return false;
            }

        }
        return true;
    }

    private double calcularEAdicionarProdutos(Venda venda, List<ProdutoVendaReq> retornoETPeQuantidades) {
        double valorTotal = 0.0;

        for (ProdutoVendaReq produtoVendaReq : retornoETPeQuantidades) {
            ETP etp = etpRepository.findById(produtoVendaReq.etpId())
                    .orElseThrow(() -> new RegistroNaoEncontradoException("ETP não encontrado"));

            ProdutoVenda produtoVenda = produtoVendaRepository.save(
                    ProdutoVendaMapper.dtoToEntity(produtoVendaReq, etp, venda)
            );

            historicoProdutoService.alterarStatus(produtoVenda.getId(), StatusHistoricoProduto.StatusHistorico.ABATIDO);

            valorTotal += calcularValorTotalProduto(produtoVendaReq);
        }

        return valorTotal;
    }

    private double calcularValorTotalProduto(ProdutoVendaReq produtoVendaReq) {
        ETP etp = etpRepository.findById(produtoVendaReq.etpId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("ETP não encontrado"));
        return etp.getProduto().getValorRevenda() * produtoVendaReq.quantidade() - produtoVendaReq.desconto();
    }

    public VendaRes finalizarVenda(Integer idVenda){
        Optional<Venda> vendaOpt = repository.findById(idVenda);
        if (vendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }

        Venda venda = vendaOpt.get();
        if (venda.getStatusVenda().getStatus().getDescricao().equals("Finalizada")){
            throw new RegistroConflitanteException("Venda já está finalizada");
        }

        // mudando o status da venda para finalizada
        Optional<StatusVenda> statusFinalizada = statusVendaRepository.findByStatus(StatusVenda.Status.FINALIZADA);
        if (statusFinalizada.isEmpty()){
            throw new RegistroNaoEncontradoException("StatusVenda não encontrado");
        }
        venda.setStatusVenda(statusFinalizada.get());
        repository.save(venda);

        List<ProdutoVenda> produtoVendas = produtoVendaRepository.findAllProdutoVendaIdVenda(idVenda);
        if (produtoVendas.isEmpty()){
            throw new RegistroNaoEncontradoException("ProdutoVenda não encontrado");
        }
        for (ProdutoVenda produtoVenda : produtoVendas) {
            historicoProdutoService.alterarStatus(produtoVenda.getId(), StatusHistoricoProduto.StatusHistorico.VENDIDO);
        }

        return VendaMapper.entityToRes(venda);
    }

    public VendaRes cancelarVenda(Integer id){
        Optional<Venda> vendaOpt = repository.findById(id);
        if (vendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }
        Venda venda = vendaOpt.get();

        if (venda.getStatusVenda().getStatus().getDescricao().equals("Cancelada")){
            throw new RegistroConflitanteException("Venda já está cancelada");
        }

        Optional<StatusVenda> statusCancelado = statusVendaRepository.findByStatus(StatusVenda.Status.CANCELADA);
        if (statusCancelado.isEmpty()){
            throw new RegistroNaoEncontradoException("StatusVenda não encontrado");
        }
        venda.setStatusVenda(statusCancelado.get());
        Venda vendaSalvada = repository.save(venda);

        List<ProdutoVenda> produtoVendas = produtoVendaRepository.findAllProdutoVendaIdVenda(id);
        if (produtoVendas.isEmpty()){
            throw new RegistroNaoEncontradoException("ProdutoVenda não encontrado");
        }
        for (ProdutoVenda produtoVenda : produtoVendas) {
            historicoProdutoService.alterarStatus(produtoVenda.getId(), StatusHistoricoProduto.StatusHistorico.DEVOLVIDO);
        }

        alterarEtpBaseadoVenda(venda.getId(), true);

        return VendaMapper.entityToRes(vendaSalvada);
    }

    public void alterarEtpBaseadoVenda(Integer idVenda, Boolean soma){
        List<RetornoETPeQuantidade> etps = produtoVendaRepository.findAllEtpsByVendaId(idVenda);
        List<ETP> etpsSalvos = new ArrayList<>();
        for (RetornoETPeQuantidade etp : etps) {
            ETP etpAtualizar = etp.etp();
            if (soma){
                etpAtualizar.setQuantidade(etpAtualizar.getQuantidade() + etp.quantidade());
            } else {
                etpAtualizar.setQuantidade(etpAtualizar.getQuantidade() - etp.quantidade());
                alertasEstoqueService.criarAlertaEstoque(etpAtualizar);
            }
            etpsSalvos.add(etpAtualizar);
        }
        // salvando a atualização de quantidade dos ETPS
        etpRepository.saveAll(etpsSalvos);
    }

    public List<VendaResTable> listarVendasPendentesPorLoja(Integer idLoja) {
        List<Venda> vendas = repository.findAllByUsuarioLojaIdAndStatusVendaStatus(idLoja, StatusVenda.Status.PENDENTE);
        if (vendas.isEmpty()){
            throw new BuscaRetornaVazioException("Nenhuma venda pendente encontrada");
        }

        List<Integer> quantidadePorProdutoVenda = new ArrayList<>();
        for (int i = 0; i < vendas.size(); i++) {
            List<RetornoETPeQuantidade> itensDaVenda = produtoVendaRepository.findAllEtpsByVendaId(vendas.get(i).getId());
            Integer qtdTotal = 0;
            for (RetornoETPeQuantidade retornoETPeQuantidade : itensDaVenda) {
                qtdTotal += retornoETPeQuantidade.quantidade();
            }
            quantidadePorProdutoVenda.add(qtdTotal);
        }
        return VendaMapper.entityToResTableList(vendas, quantidadePorProdutoVenda);
    }

    public VendaDetalhamentoRes buscarVendaDetalhadaPorId(Integer idVenda) {
        Optional<Venda> venda = repository.findById(idVenda);
        if(venda.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }
        List<ProdutoVenda> produtosVenda = produtoVendaRepository.findAllProdutoVendaIdVenda(idVenda);
        List<ProdutoVendaDetalhamentoRes> produtosDetalhados = ProdutoVendaMapper.entityToResDetalhamento(produtosVenda);
        VendaDetalhamentoRes vendaDetalhada = VendaMapper.entityToResDetalhamento(venda.get(), produtosDetalhados);
        return vendaDetalhada;
    }

    public VendaRes realizarTroca(Integer idVenda, TrocaReq req, List<ProdutoVendaReq> retornoETPeQuantidades) {
        Usuario usuario = usuarioRepository.findByCodigoVenda(req.codigoVendedor())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Usuario não encontrado"));

        StatusVenda statusEmAndamento = statusVendaRepository.findByStatus(StatusVenda.Status.PENDENTE)
                .orElseThrow(() -> new RegistroNaoEncontradoException("StatusVenda não encontrado"));

        Venda venda = vendaRepository.findById(idVenda).orElseThrow(() -> new RegistroNaoEncontradoException("Venda não encontrada"));

        double totalVenda = 0.0;
        List<ProdutoVenda> allEtpsByVendaId = produtoVendaRepository.findAllProdutoVendaIdVenda(idVenda);

        // Process existing products
        for (ProdutoVenda produtoVendaAnterior : allEtpsByVendaId) {
            Optional<ProdutoVendaReq> produtoVendaOptional = retornoETPeQuantidades.stream()
                    .filter(etpNovo -> etpNovo.etpId().equals(produtoVendaAnterior.getEtp().getId()))
                    .findAny();

            if (produtoVendaOptional.isPresent()) {
                totalVenda += processExistingProduct(produtoVendaAnterior, produtoVendaOptional.get());
            } else {
                processRemovedProduct(produtoVendaAnterior);
            }
        }

        // Process new products
        List<ProdutoVendaReq> novosProdutosVenda = retornoETPeQuantidades.stream()
                .filter(produtoVendaReq -> allEtpsByVendaId.stream()
                        .noneMatch(produtoVenda -> produtoVenda.getEtp().getId()==produtoVendaReq.etpId()))
                .toList();

        for (ProdutoVendaReq produtoVendaReq : novosProdutosVenda) {
            totalVenda += processNewProduct(produtoVendaReq, venda);
        }

        // Update sale status and total value
        venda.setStatusVenda(statusEmAndamento);
        venda.setUsuario(usuario);
        venda.setValorTotal(totalVenda);
        venda = repository.save(venda);

        return VendaMapper.entityToRes(venda);
    }

    private double processExistingProduct(ProdutoVenda produtoVendaAnterior, ProdutoVendaReq produtoVendaEncontrado) {
        Optional<ETP> etpDoProdutoVendaOptional = etpRepository.findById(produtoVendaEncontrado.etpId());
        if (etpDoProdutoVendaOptional.isEmpty()) {
            throw new RegistroNaoEncontradoException("ETP não encontrado");
        }

        ETP etpDoProdutoVenda = etpDoProdutoVendaOptional.get();
        int diferenca = produtoVendaAnterior.getQuantidade() - produtoVendaEncontrado.quantidade();

        if (diferenca < 0) {
            // Received more items of the same product
            int total = produtoVendaEncontrado.quantidade() - produtoVendaAnterior.getQuantidade();
            if (total >= 0 && total <= etpDoProdutoVenda.getQuantidade()) {
                produtoVendaAnterior.setQuantidade(produtoVendaEncontrado.quantidade());
                produtoVendaRepository.save(produtoVendaAnterior);
                etpDoProdutoVenda.setQuantidade(etpDoProdutoVenda.getQuantidade() - (diferenca * -1));
                etpRepository.save(etpDoProdutoVenda);
                historicoProdutoService.alterarStatus(produtoVendaAnterior.getId(), StatusHistoricoProduto.StatusHistorico.ABATIDO);
                alertasEstoqueService.criarAlertaEstoque(etpDoProdutoVenda);
            } else {
                throw new OperacaoInvalidaException("ProdutoVenda - Quantidade de itens não pode ser maior o que tem em estoque");
            }
        } else if (diferenca >= 1) {
            // A product was removed from this ProdutoVenda
            produtoVendaAnterior.setQuantidade(produtoVendaEncontrado.quantidade());
            produtoVendaRepository.save(produtoVendaAnterior);
            etpDoProdutoVenda.setQuantidade(etpDoProdutoVenda.getQuantidade() + diferenca);
            etpRepository.save(etpDoProdutoVenda);
            alertasEstoqueService.criarAlertaEstoque(etpDoProdutoVenda);
            historicoProdutoService.alterarStatus(produtoVendaAnterior.getId(), StatusHistoricoProduto.StatusHistorico.ABATIDO);
        }

        return calcularValorTotalProduto(produtoVendaEncontrado);
    }

    private void processRemovedProduct(ProdutoVenda produtoVendaAnterior) {
        ETP etpDoProdutoVenda = etpRepository.findById(produtoVendaAnterior.getEtp().getId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("ETP não encontrado"));

        List<HistoricoProdutoRes> historicoProdutoRes = historicoProdutoService.pesquisarPorProdutoVenda(produtoVendaAnterior.getId());
        if (!historicoProdutoRes.isEmpty()) {
            HistoricoProdutoRes ultimoHistorico = historicoProdutoRes.get(historicoProdutoRes.size() - 1);
            if (!ultimoHistorico.getStatusHistoricoProduto().getStatus().equals(StatusHistoricoProduto.StatusHistorico.DEVOLVIDO)) {
                etpDoProdutoVenda.setQuantidade(etpDoProdutoVenda.getQuantidade() + produtoVendaAnterior.getQuantidade());
                etpRepository.save(etpDoProdutoVenda);
                historicoProdutoService.alterarStatus(produtoVendaAnterior.getId(), StatusHistoricoProduto.StatusHistorico.DEVOLVIDO);
                alertasEstoqueService.criarAlertaEstoque(etpDoProdutoVenda);
            }
        }
    }

    private double processNewProduct(ProdutoVendaReq produtoVendaReq, Venda venda) {
        ETP etp = etpRepository.findById(produtoVendaReq.etpId()).orElseThrow(() -> new RegistroNaoEncontradoException("ETP não encontrado!"));
        if (etp.getQuantidade() >= produtoVendaReq.quantidade()) {
            ProdutoVenda produtoVendaNovoSave = produtoVendaRepository.save(ProdutoVendaMapper.dtoToEntity(
                    new ProdutoVendaTrocaReq(
                            produtoVendaReq.etpId(),
                            produtoVendaReq.quantidade(),
                            produtoVendaReq.desconto(),
                            etp.getItemPromocional()),
                    etp,
                    venda));

            etp.setQuantidade(etp.getQuantidade() - produtoVendaReq.quantidade());
            etpRepository.save(etp);
            alertasEstoqueService.criarAlertaEstoque(etp);
            historicoProdutoService.alterarStatus(produtoVendaNovoSave.getId(), StatusHistoricoProduto.StatusHistorico.ABATIDO);
            return calcularValorTotalProduto(produtoVendaReq);
        } else {
            throw new OperacaoInvalidaException("ProdutoVenda - Quantidade de itens não pode ser maior o que tem em estoque");
        }
    }
}
