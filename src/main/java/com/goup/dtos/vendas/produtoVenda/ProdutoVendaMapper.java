package com.goup.dtos.vendas.produtoVenda;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.historico.produto.HistoricoProdutoMapper;
import com.goup.dtos.vendas.venda.VendaMapper;
import com.goup.entities.estoque.ETP;
import com.goup.entities.historicos.HistoricoProduto;
import com.goup.entities.vendas.ProdutoVenda;
import com.goup.entities.vendas.Venda;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProdutoVendaMapper {
    public static ProdutoVenda dtoToEntity(ProdutoVendaReq produtoVendaReq, ETP etp, Venda venda){
        ProdutoVenda produtoVenda = new ProdutoVenda();
        produtoVenda.setValorUnitario(produtoVendaReq.valorUnitario());
        produtoVenda.setQuantidade(produtoVendaReq.quantidade());
        produtoVenda.setDesconto(produtoVendaReq.desconto());
//        produtoVenda.setItemPromocional(produtoVendaReq.itemPromocional());
        produtoVenda.setEtp(etp);
        produtoVenda.setVenda(venda);
        return produtoVenda;
    }

    public static ProdutoVendaRes entityToResDto(ProdutoVenda produtoVenda) {
        ProdutoVendaRes produtoVendaRes = new ProdutoVendaRes();
        produtoVendaRes.setId(produtoVenda.getId());
        produtoVendaRes.setValorUnitario(produtoVenda.getValorUnitario());
        produtoVendaRes.setQuantidade(produtoVenda.getQuantidade());
        produtoVendaRes.setDesconto(produtoVenda.getDesconto());
//        produtoVendaRes.setItemPromocional(produtoVenda.getItemPromocional().name());
        produtoVendaRes.setVenda(VendaMapper.entityToRes(produtoVenda.getVenda()));
        produtoVendaRes.setEtp(ETPMapper.toTableResponseEntity(produtoVenda.getEtp()));
        List<HistoricoProduto> historicoProduto = produtoVenda.getHistoricoProduto();

        List<ProdutoVendaRes.HistoricoProdutoListagem> historicoProdutoListagem = new ArrayList<>();

        if (historicoProduto != null) {
            for (HistoricoProduto produto : historicoProduto) {
                ProdutoVendaRes.HistoricoProdutoListagem historicoProdutoDto = new ProdutoVendaRes.HistoricoProdutoListagem();
                historicoProdutoDto.setId(produto.getId());
                historicoProdutoDto.setDataHora(produto.getDataHora());
                historicoProdutoDto.setStatusHistoricoProduto(produto.getStatusHistoricoProduto());
                historicoProdutoListagem.add(historicoProdutoDto);
            }
        }
        produtoVendaRes.setHistoricos(historicoProdutoListagem);
        return produtoVendaRes;
    }

    public static List<ProdutoVendaRes> dtoListToEntityList(List<ProdutoVenda> produtoVendas){
        return produtoVendas.stream().map(ProdutoVendaMapper::entityToResDto).toList();
    }

    public static List<ProdutoVenda> dtoListToEntityList(List<ProdutoVendaReq> prodVendaReqs, ETP etp, Venda venda){
        return prodVendaReqs.stream().map(prodVendaReq -> ProdutoVendaMapper.dtoToEntity(prodVendaReq, etp, venda)).toList();
    }

    public static ProdutoVendaDetalhamentoRes entityToResDetalhamento(ProdutoVenda p){
        // Todos atributos são referentes apenas a uma unidade, exceto Subtotal
        Integer id = p.getId();
        String codigo = p.getEtp().getProduto().getModelo().getCodigo();
        String descricao = p.getEtp().getProduto().getNome();
        Double precoUnitario = p.getValorUnitario();
        Integer qtd = p.getQuantidade();
        Double desconto = p.getDesconto();
        Double descontoUnitario = p.getDesconto() / qtd;
        Double precoLiquido = precoUnitario - descontoUnitario;
        Double totalBruto = precoUnitario * qtd;
        Double subtotal = precoLiquido * qtd;

        ProdutoVendaDetalhamentoRes dto = new ProdutoVendaDetalhamentoRes(
            id, codigo, descricao, precoUnitario, qtd, desconto, precoLiquido, totalBruto, subtotal
        );
        return dto;
    }

    public static List<ProdutoVendaDetalhamentoRes> entityToResDetalhamento(List<ProdutoVenda> produtosVenda) {
        return produtosVenda.stream().map(ProdutoVendaMapper::entityToResDetalhamento).toList();
    }
}
