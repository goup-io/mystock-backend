package com.goup.services.vendas;

import com.goup.dtos.vendas.TipoVendaDescontoReq;
import com.goup.dtos.vendas.TipoVendaMapper;
import com.goup.dtos.vendas.TipoVendaReq;
import com.goup.dtos.vendas.TipoVendaRes;
import com.goup.entities.vendas.TipoVenda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.vendas.TipoVendaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVendaService {
    @Autowired
    private TipoVendaRepository repository;

    public List<TipoVendaRes> listar(){
        List<TipoVenda> tiposVenda = repository.findAll();
        if (tiposVenda.isEmpty()){
            throw new BuscaRetornaVazioException("TipoVenda retornou vazio");
        }
        return TipoVendaMapper.entityToResList(tiposVenda);
    }

    public TipoVendaRes atualizar(@Valid TipoVendaReq tipoVendaReq, Integer id){
        TipoVenda tipoVendaEncontrado = buscarTipoVenda(id);
        TipoVenda tipoVendaAtualizado = repository.save(TipoVendaMapper.atualizarTipoVenda(tipoVendaReq, tipoVendaEncontrado));
        return TipoVendaMapper.entityToRes(tipoVendaAtualizado);
    }

    public TipoVendaRes atualizarDesconto(@Valid TipoVendaDescontoReq dtoReq, Integer id){
        TipoVenda tipoVendaEncontrado = buscarTipoVenda(id);
        TipoVenda tipoVendaAtt = repository.save(TipoVendaMapper.atualizarDescontoTipoVenda(dtoReq, tipoVendaEncontrado));
        return TipoVendaMapper.entityToRes(tipoVendaAtt);
    }

    public TipoVenda buscarTipoVenda(Integer id){
        Optional<TipoVenda> tipoVendaEncontrado = repository.findById(id);
        if (tipoVendaEncontrado.isEmpty()){
            throw new RegistroNaoEncontradoException("TipoVenda não encontrado");
        }
        return tipoVendaEncontrado.get();
    }

}
