package com.goup.dtos.vendas.venda;

import com.goup.dtos.vendas.produtoVenda.ProdutoVendaDetalhamentoRes;
import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VendaMapper {
    public static VendaRes entityToRes(Venda venda){
        return new VendaRes(
                venda.getId(),
                venda.getDataHora(),
                venda.getDesconto(),
                venda.getValorTotal(),
                venda.getStatusVenda().getStatus().getDescricao(),
                venda.getTipoVenda().getTipo().getTipo(),
                venda.getUsuario().getNome()
        );
    }

    public static Venda reqToEntity(VendaReq req, Usuario usuario, TipoVenda tipoVenda, StatusVenda statusVenda){
        Venda venda = new Venda();
        venda.setDataHora(LocalDateTime.now());
        venda.setDesconto(req.desconto());
        venda.setTipoVenda(tipoVenda);
        venda.setUsuario(usuario);
        venda.setStatusVenda(statusVenda);
        return venda;
    }

    public static VendaResTable entityToResTable(Venda venda, Integer quantidadeItens){
        return new VendaResTable(
                venda.getId(),
                venda.getDataHora().toLocalDate(),
                venda.getDataHora().toLocalTime(),
                venda.getUsuario().getCodigoVenda().toString(),
                venda.getUsuario().getNome(),
                venda.getTipoVenda(),
                quantidadeItens,
                venda.getValorTotal(),
                venda.getStatusVenda().getStatus().getDescricao()
        );
    }
    public static List<VendaResTable> entityToResTableList(List<Venda> vendas, List<Integer> quantidadeItens){
        List<VendaResTable> vendaResTables = new ArrayList<>();
        for (int i = 0; i < vendas.size(); i++) {
            vendaResTables.add(entityToResTable(vendas.get(i), quantidadeItens.get(i)));
        }
        return vendaResTables;
    }

    public static List<VendaRes> entityToRes(List<Venda> vendas){
        return vendas.stream().map(VendaMapper::entityToRes).toList();
    }

    public static VendaDetalhamentoRes entityToResDetalhamento(Venda v, List<ProdutoVendaDetalhamentoRes> produtosDetalhados) {
        Integer id = v.getId();
        LocalDate data = v.getDataHora().toLocalDate();
        LocalTime hora = v.getDataHora().toLocalTime();
        String nomeVendedor = v.getUsuario().getNome();
        TipoVenda tipoVenda = v.getTipoVenda();
        Integer qtdItens = 0;
        Double valor = v.getValorTotal();
        String statusVenda = v.getStatusVenda().getStatus().getDescricao();
        for(ProdutoVendaDetalhamentoRes p : produtosDetalhados){
            qtdItens += p.qtd();
        }
        VendaDetalhamentoRes dto = new VendaDetalhamentoRes(id, data, hora, nomeVendedor, tipoVenda, qtdItens, valor, statusVenda, produtosDetalhados);
        return dto;
    }
}
