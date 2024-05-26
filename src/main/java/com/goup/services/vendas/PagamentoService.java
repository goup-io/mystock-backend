package com.goup.services.vendas;

import com.goup.dtos.vendas.pagamento.PagamentoMapper;
import com.goup.dtos.vendas.pagamento.PagamentoReq;
import com.goup.dtos.vendas.pagamento.PagamentoRes;
import com.goup.entities.vendas.Pagamento;
import com.goup.entities.vendas.TipoPagamento;
import com.goup.entities.vendas.Venda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.vendas.PagamentoRepository;
import com.goup.repositories.vendas.TipoPagamentoRepository;
import com.goup.repositories.vendas.VendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public PagamentoRes realizarPagamento(@Valid PagamentoReq dtoPagamento){
        Venda venda = vendaRepository.findById(dtoPagamento.idVenda())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Venda não encontrada"));
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(dtoPagamento.idTipoPagamento())
                .orElseThrow(() -> new RegistroNaoEncontradoException("TipoPagamento não encontrado"));
        Pagamento pagamento = repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, tipoPagamento, venda));
        return PagamentoMapper.entityToDto(pagamento);
    }

    public List<PagamentoRes> listar(){
        List<Pagamento> pagamentos = repository.findAll();
        if(pagamentos.isEmpty()){
            throw new BuscaRetornaVazioException("Pagamentos retornou vazio");
        }
        return PagamentoMapper.pagamentoResList(pagamentos);
    }

    public PagamentoRes buscarPorId(Integer id){
        Pagamento pagamento = repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Pagamento não encontrado"));
        return PagamentoMapper.entityToDto(pagamento);
    }



}
