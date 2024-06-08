package com.goup.services.vendas;

import com.goup.dtos.vendas.pagamento.PagamentoMapper;
import com.goup.dtos.vendas.pagamento.PagamentoReq;
import com.goup.dtos.vendas.pagamento.PagamentoRes;
import com.goup.entities.vendas.Pagamento;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoPagamento;
import com.goup.entities.vendas.Venda;
import com.goup.exceptions.BuscaRetornaVazioException;
import com.goup.exceptions.RegistroConflitanteException;
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
        Venda venda = vendaRepository.findById(dtoPagamento.getIdVenda())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Venda não encontrada"));
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(dtoPagamento.getIdTipoPagamento())
                .orElseThrow(() -> new RegistroNaoEncontradoException("TipoPagamento não encontrado"));

        Double valorPagoAteMomento = repository.sumValorPago(venda.getId()) == null ? 0.0 : repository.sumValorPago(venda.getId());
        Double valorRestante = venda.getValorTotal() - valorPagoAteMomento;
        Pagamento pagamento;
        if ((valorPagoAteMomento >= venda.getValorTotal())){
            throw new RegistroConflitanteException("Pagamento da Venda já foi realizado");
        } else if (venda.getStatusVenda().getStatus() == StatusVenda.Status.FINALIZADA) {
            throw new RegistroConflitanteException("Pagamento Impossivel de ser realizado - A venda já foi finalizada");
        } else if (venda.getStatusVenda().getStatus() == StatusVenda.Status.CANCELADA) {
            throw new RegistroConflitanteException("Pagamento Impossivel de ser realizado - A venda foi cancelada");
        }
        if (valorPagoAteMomento + dtoPagamento.getValor() > venda.getValorTotal()){
            if (tipoPagamento.getMetodo().getMetodo().equals("PIX")){
                pagamento = pagarComPix(dtoPagamento, tipoPagamento, venda);
            } else {
                pagamento = repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, valorRestante, tipoPagamento, venda));
            }
        } else {
            if (tipoPagamento.getMetodo().getMetodo().equals("PIX")){
                pagamento = pagarComPix(dtoPagamento, tipoPagamento, venda);
            } else {
                  pagamento = repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, dtoPagamento.getValor(), tipoPagamento, venda));
            }
        }

        return PagamentoMapper.entityToDto(pagamento, venda.getValorTotal() - (valorPagoAteMomento + dtoPagamento.getValor()));
    }

    public Pagamento pagarComPix(PagamentoReq dtoPagamento, TipoPagamento tipoPagamento, Venda venda){
        //todo: lógica para pagar o pix


        return repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, dtoPagamento.getValor(), tipoPagamento, venda));
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
