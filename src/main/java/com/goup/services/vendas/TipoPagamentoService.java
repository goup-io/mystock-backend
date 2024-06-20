package com.goup.services.vendas;

import com.goup.dtos.vendas.tipoPagamento.TipoPagamentoMapper;
import com.goup.dtos.vendas.tipoPagamento.TipoPagamentoRes;
import com.goup.entities.vendas.TipoPagamento;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.vendas.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPagamentoService {
    @Autowired
    private TipoPagamentoRepository repository;

    public List<TipoPagamentoRes> listar(){
        List<TipoPagamento> tipoPagamentos = repository.findAll();
        if (tipoPagamentos.isEmpty()){
            throw new BuscaRetornaVazioException("TipoPagamentos está vazio");
        }
        return TipoPagamentoMapper.entityToResList(tipoPagamentos);
    }

    public TipoPagamentoRes buscarTipoPagamento(Integer id){
        Optional<TipoPagamento> tipoPagamento = repository.findById(id);
        if (tipoPagamento.isEmpty()){
            throw new RegistroNaoEncontradoException("TipoPagamento não encontrado");
        }
        return TipoPagamentoMapper.entityToRes(tipoPagamento.get());
    }
}
