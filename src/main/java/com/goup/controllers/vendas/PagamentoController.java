package com.goup.controllers.vendas;

import com.azure.core.annotation.Get;
import com.goup.dtos.vendas.pagamento.PagamentoFluxoRes;
import com.goup.dtos.vendas.pagamento.PagamentoReq;
import com.goup.dtos.vendas.pagamento.PagamentoRes;
import com.goup.services.vendas.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${mystock.api.prefix}/pagamentos")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/qrCodePix")
    public ResponseEntity<String> criarQrCode(@Valid @RequestParam Double valorPix) {
        return ResponseEntity.status(201).body(pagamentoService.pagarComPix(valorPix));
    }

    @PostMapping
    public ResponseEntity<PagamentoRes> criarPagamento(@Valid @RequestBody PagamentoReq pagamentoReq) {
        return ResponseEntity.status(201).body(pagamentoService.realizarPagamento(pagamentoReq));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoRes>> listarPagamentos() {
        return ResponseEntity.status(200).body(pagamentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoRes> buscarPagamentoPorId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(pagamentoService.buscarPorId(id));
    }

    @GetMapping("/fluxo-pagamento/{idVenda}")
    public ResponseEntity<List<PagamentoFluxoRes>> buscarFluxoPagamentoPorId(@PathVariable Integer idVenda) {

        List<PagamentoFluxoRes> pagamentos = pagamentoService.listarPorVenda(idVenda);

        if (pagamentos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(pagamentos);
    }
}
