package com.goup.services.vendas;

import com.goup.dtos.vendas.pagamento.PagamentoFluxoRes;
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
import pix.DadosEnvioPix;
import pix.QRCodePix;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public PagamentoRes realizarPagamento(@Valid PagamentoReq dtoPagamento) {
        Venda venda = vendaRepository.findById(dtoPagamento.getIdVenda())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Venda não encontrada"));
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findById(dtoPagamento.getIdTipoPagamento())
                .orElseThrow(() -> new RegistroNaoEncontradoException("TipoPagamento não encontrado"));

        Double valorPagoAteMomento = repository.sumValorPago(venda.getId()) == null ? 0.0 : repository.sumValorPago(venda.getId());
        Double valorRestante = venda.getValorTotal() - valorPagoAteMomento;
        Pagamento pagamento = null;
        if ((valorPagoAteMomento >= venda.getValorTotal())) {
            throw new RegistroConflitanteException("Pagamento da Venda já foi realizado");
        } else if (venda.getStatusVenda().getStatus() == StatusVenda.Status.FINALIZADA) {
            throw new RegistroConflitanteException("Pagamento Impossivel de ser realizado - A venda já foi finalizada");
        } else if (venda.getStatusVenda().getStatus() == StatusVenda.Status.CANCELADA) {
            throw new RegistroConflitanteException("Pagamento Impossivel de ser realizado - A venda foi cancelada");
        }

        String base64Image = null;

        if (valorPagoAteMomento + dtoPagamento.getValor() <= venda.getValorTotal()) {
            if (tipoPagamento.getMetodo().getMetodo().equals("PIX")) {
                pagamento = repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, dtoPagamento.getValor(), tipoPagamento, venda));
                base64Image = pagarComPix(dtoPagamento);
            } else {
                pagamento = repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, dtoPagamento.getValor(), tipoPagamento, venda));
            }
            Double valorQueResta = venda.getValorTotal() - (valorPagoAteMomento + dtoPagamento.getValor());
            return PagamentoMapper.entityToDto(pagamento, valorQueResta, base64Image);
        } else if (valorPagoAteMomento + dtoPagamento.getValor() > venda.getValorTotal() && tipoPagamento.getMetodo().getMetodo().equals("Dinheiro")) {
            Double valorQueResta = venda.getValorTotal() - (valorPagoAteMomento + dtoPagamento.getValor());
            pagamento = repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, valorRestante, tipoPagamento, venda));
            return PagamentoMapper.entityToDto(pagamento, valorQueResta, base64Image);
        } else {
            throw new RegistroConflitanteException("Valor do pagamento excede o valor da venda");
        }
    }

    public String pagarComPix(PagamentoReq dtoPagamento){
            final var imagePath = "qrcode.png";

            final var dadosPix =
                    new DadosEnvioPix(
                            "Perolas Calcados", "38637406882",
                            new BigDecimal(dtoPagamento.getValor()), "São Paulo", "Pérolas Calçados");

            final var qrCodePix = new QRCodePix(dadosPix);
                qrCodePix.save(Path.of(imagePath));
                System.out.println("QRCode:");
                System.out.println(qrCodePix);
                System.out.printf("%nArquivo gerado em "+imagePath);


        String base64Image = qrCodePix.saveAndGetBytesAsBase64(Path.of(imagePath));

        return base64Image;
        //return repository.save(PagamentoMapper.dtoToEntity(dtoPagamento, dtoPagamento.getValor(), tipoPagamento, venda));
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

    public List<PagamentoFluxoRes> listarPorVenda(Integer idVenda){
        List<Pagamento> pagamentos = repository.findAllByVenda_Id(idVenda);
        return PagamentoMapper.pagamentoFluxoResList(pagamentos);
    }

}
