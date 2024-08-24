package com.goup.controllers.vendas;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaReq;
import com.goup.dtos.vendas.venda.*;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;
import com.goup.services.vendas.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService service;
    private static final Logger logger = LoggerFactory.getLogger(VendaController.class);

    @GetMapping
    public ResponseEntity<List<VendaResTable>> listar(){
        return ResponseEntity.status(200).body(service.listar());
    }
    @Operation(description = descricao)
    @GetMapping("/filtro")
    public ResponseEntity<List<VendaResTable>> listarPorFiltro(
        @RequestParam(required = false) Integer id_tipo_venda,
        @RequestParam(required = false) Integer id_vendedor,
        @RequestParam(required = false) LocalDateTime dataHoraInicio,
        @RequestParam(required = false) LocalDateTime dataHoraFim,
        @RequestParam(required = false) Integer id_loja,
        @RequestParam(required = false) Integer id_status
    ){
        List<VendaResTable> vendas = service.listarPorFiltro(id_tipo_venda, id_vendedor, dataHoraInicio, dataHoraFim, id_loja, id_status);
        return vendas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResTable> buscar(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @GetMapping("/detalhamento/{idVenda}")
    public ResponseEntity<VendaDetalhamentoRes> buscarVendaDetalhadaPorId(@PathVariable Integer idVenda){
        return ResponseEntity.status(200).body(service.buscarVendaDetalhadaPorId(idVenda));
    }

    @PostMapping
    public ResponseEntity<VendaRes> criar(@Valid @RequestBody VendaEProdutosReq reqDto) {
        return ResponseEntity.status(201).body(service.salvar(reqDto.vendaReq(), reqDto.produtosVendaReq()));
    }

    @PatchMapping("/cancelar/{id}")
    ResponseEntity<VendaRes> cancelarVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.cancelarVenda(id));
    }

    @PatchMapping("/finalizar/{id}")
    ResponseEntity<VendaRes> finalizarVenda(@PathVariable Integer id){
        return ResponseEntity.status(200).body(service.finalizarVenda(id));
    }

    @GetMapping("/vendas-pendentes/{idLoja}")
    public ResponseEntity<List<VendaResTable>> listarVendasPendentes(@PathVariable Integer idLoja){
        return ResponseEntity.status(200).body(service.listarVendasPendentesPorLoja(idLoja));
    }

    @PutMapping("/trocar/{idVenda}")
    public ResponseEntity<VendaRes> realizarTroca(@PathVariable Integer idVenda, @Valid @RequestBody TrocaEProdutosReq vendaEProdutosReq){
            logger.info("Recebendo requisição de troca para venda ID: {}", idVenda);
            logger.info("TrocaReq: {}", vendaEProdutosReq.trocaReq());
            logger.info("ProdutoVendaReqs: {}", vendaEProdutosReq.produtoVendaReqs());
        return ResponseEntity.status(201).body(service.realizarTroca(idVenda, vendaEProdutosReq.trocaReq(), vendaEProdutosReq.produtoVendaReqs()));
    }


    private static final String descricao = """
        <p>StatusVenda:</p>
        <ul>
            <li>1 - Pendente</li>
            <li>2 - Finalizada</li>
            <li>3 - Cancelada</li>      
        </ul>
        <p>TipoVenda:</p>
        <ul>
            <li>1 - Varejo</li>
            <li>2 - Atacado</li>
            <li>3 - Especial</li>
        </ul>
        """;
}
