package com.goup.services.vendas;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaMapper;
import com.goup.dtos.vendas.produtoVenda.ProdutoVendaReq;
import com.goup.dtos.vendas.produtoVenda.ProdutoVendaRes;
import com.goup.entities.estoque.ETP;
import com.goup.entities.vendas.ProdutoVenda;
import com.goup.entities.vendas.Venda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.produtos.ETPRepository;
import com.goup.repositories.vendas.ProdutoVendaRepository;
import com.goup.repositories.vendas.VendaRepository;
import com.goup.utils.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoVendaService{
    @Autowired
    private ProdutoVendaRepository repository;
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private VendaRepository vendaRepository;


    // este método deve ser chamado no momento da criação da venda, assim criando os ProdutoVenda juntamente com a venda
    public List<ProdutoVendaRes> adicionarVendaNosProdutosVenda(List<ProdutoVendaReq> produtoVendaReqs, Integer idVenda){
        Optional<Venda> vendaOpt = vendaRepository.findById(idVenda);
        if (vendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("Venda não encontrada");
        }
        List<ProdutoVendaRes> produtoVendaResList = new ArrayList<>();
        produtoVendaReqs.forEach(produtoVendaReq -> {
            Optional<ETP> etpOpt = etpRepository.findById(produtoVendaReq.etpId());
            if (etpOpt.isEmpty()){
                throw new RegistroNaoEncontradoException("ETP não encontrado");
            }
            ProdutoVenda produtoVenda = repository.save(ProdutoVendaMapper.dtoToEntity(produtoVendaReq, etpOpt.get(), vendaOpt.get()));
            produtoVendaResList.add(ProdutoVendaMapper.entityToResDto(produtoVenda));
        });
        return produtoVendaResList;
    }

    public List<ProdutoVendaRes> listar(){
        List<ProdutoVenda> produtoVendas = repository.findAll();
        if (produtoVendas.isEmpty()){
            throw new BuscaRetornaVazioException("ProdutoVenda retorna lista vazia");
        }

        return ProdutoVendaMapper.dtoListToEntityList(produtoVendas);
    }

    public ProdutoVendaRes buscarPorId(Integer id){
        Optional<ProdutoVenda> produtoVendaOpt = repository.findById(id);
        if (produtoVendaOpt.isEmpty()){
            throw new RegistroNaoEncontradoException("ProdutoVenda não encontrado");
        }
        return ProdutoVendaMapper.entityToResDto(produtoVendaOpt.get());
    }

}
