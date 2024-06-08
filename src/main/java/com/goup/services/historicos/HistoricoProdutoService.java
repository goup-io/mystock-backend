package com.goup.services.historicos;

import com.goup.dtos.historico.produto.HistoricoProdutoMapper;
import com.goup.dtos.historico.produto.HistoricoProdutoReq;
import com.goup.dtos.historico.produto.HistoricoProdutoRes;
import com.goup.entities.historicos.HistoricoProduto;
import com.goup.entities.historicos.StatusHistoricoProduto;
import com.goup.entities.vendas.ProdutoVenda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroConflitanteException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.historicos.HistoricoProdutoRepository;
import com.goup.repositories.historicos.StatusHistoricoProdRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoProdutoService {
    @Autowired
    private HistoricoProdutoRepository repository;
    @Autowired
    private StatusHistoricoProdRepository statusHistoricoProdRepository;
    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    /*
    public HistoricoProdutoRes criarHistorico(HistoricoProdutoReq historicoProdutoReq){
        Optional<HistoricoProduto> itemHistoricoOpt = repository.findByProdutoVenda_id(historicoProdutoReq.idProdutoVenda());
        if (itemHistoricoOpt.isPresent()){
            throw new RegistroConflitanteException("HistoricoProduto já existe");
        }

        ProdutoVenda produtoVenda = produtoVendaRepository.findById(historicoProdutoReq.idProdutoVenda())
                .orElseThrow(() -> new RegistroNaoEncontradoException("ProdutoVenda não encontrado com o id: " + historicoProdutoReq.idProdutoVenda()));
        StatusHistoricoProduto status = statusHistoricoProdRepository.findByStatus(historicoProdutoReq.statusHistorico())
                .orElseThrow(() -> new RegistroNaoEncontradoException("StatusHistoricoProduto não encontrado com o status: ABATIDO"));

        HistoricoProduto historicoProduto = repository.save(HistoricoProdutoMapper.reqToEntity(status, produtoVenda));

        return HistoricoProdutoMapper.entitytoRes(historicoProduto);
    }
     */

    public HistoricoProdutoRes alterarStatus(Integer idProdutoVenda, StatusHistoricoProduto.StatusHistorico statusHistorico){
        ProdutoVenda produtoVenda = produtoVendaRepository.findById(idProdutoVenda)
                .orElseThrow(() -> new RegistroNaoEncontradoException("ProdutoVenda não encontrado com o id: " + idProdutoVenda));
        StatusHistoricoProduto status = statusHistoricoProdRepository.findByStatus(statusHistorico)
                .orElseThrow(() -> new RegistroNaoEncontradoException("StatusHistoricoProduto não encontrado com o status: " + statusHistorico));

        HistoricoProduto savedHistoricoProduto = repository.save(HistoricoProdutoMapper.reqToEntity(status, produtoVenda));

        return HistoricoProdutoMapper.entitytoRes(savedHistoricoProduto);
    }

    public List<HistoricoProdutoRes> listar(){
        List<HistoricoProduto> historicoProdutos = repository.findAll();
        if (historicoProdutos.isEmpty()){
            throw new BuscaRetornaVazioException("HistoricoProduto retornou uma lista vazia");
        }
        return HistoricoProdutoMapper.listRes(historicoProdutos);
    }

    public HistoricoProdutoRes procurarPorId(Integer id){
        HistoricoProduto historicoProduto = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("HistoricoProduto não encontrado com o id: " + id));
        return HistoricoProdutoMapper.entitytoRes(historicoProduto);
    }

    public List<HistoricoProdutoRes> pesquisarPorProdutoVenda(Integer idProdutoVenda){
        Optional<ProdutoVenda> produtoVenda = produtoVendaRepository.findById(idProdutoVenda);
        if (produtoVenda.isEmpty()){
            throw new RegistroNaoEncontradoException("ProdutoVenda não encontrado");
        }
        List<HistoricoProduto> historicoProdutos = repository.findByProdutoVenda_id(idProdutoVenda);
        if (historicoProdutos.isEmpty()){
            throw new BuscaRetornaVazioException("HistoricoProduto retornou uma lista vazia");
        }
        return HistoricoProdutoMapper.listRes(historicoProdutos);
    }
}
