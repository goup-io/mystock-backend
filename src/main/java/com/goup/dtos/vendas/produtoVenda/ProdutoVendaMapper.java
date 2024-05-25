package com.goup.dtos.vendas.produtoVenda;

import com.goup.dtos.estoque.ETPMapper;
import com.goup.dtos.vendas.venda.VendaMapper;
import com.goup.entities.estoque.ETP;
import com.goup.entities.vendas.ProdutoVenda;
import com.goup.entities.vendas.Venda;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProdutoVendaMapper {
    public static ProdutoVenda dtoToEntity(ProdutoVendaReq produtoVendaReq, ETP etp, Venda venda){
        ProdutoVenda produtoVenda = new ProdutoVenda();
        produtoVenda.setValorUnitario(produtoVendaReq.valorUnitario());
        produtoVenda.setQuantidade(produtoVendaReq.quantidade());
        produtoVenda.setDesconto(produtoVendaReq.desconto());
        produtoVenda.setItemPromocional(produtoVendaReq.itemPromocional());
        produtoVenda.setEtp(etp);
        produtoVenda.setVenda(venda);
        return produtoVenda;
    }

    public static ProdutoVendaRes entityToResDto(ProdutoVenda produtoVenda){
        return new ProdutoVendaRes(
                produtoVenda.getId(),
                produtoVenda.getValorUnitario(),
                produtoVenda.getQuantidade(),
                produtoVenda.getDesconto(),
                produtoVenda.getItemPromocional().name(),
                VendaMapper.entityToRes(produtoVenda.getVenda()),
                ETPMapper.entityToRes(produtoVenda.getEtp())

        );
    }

    public static List<ProdutoVenda> dtoListToEntityList(List<ProdutoVendaReq> prodVendaReqs, ETP etp, Venda venda){
        return prodVendaReqs.stream().map(prodVendaReq -> ProdutoVendaMapper.dtoToEntity(prodVendaReq, etp, venda)).toList();
    }
}
