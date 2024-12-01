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
                venda.getUsuario() != null ? venda.getUsuario().getNome() : "---"
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
                venda.getUsuario() != null ? venda.getUsuario().getCodigoVenda().toString() : "---",
                venda.getUsuario() != null ? venda.getUsuario().getNome() : "---",
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

    public static List<VendaResTable> listToListRes (List<Venda> vendas){
        List<VendaResTable> res = new ArrayList<>();
        for (Venda v : vendas) {
            res.add(entityToResTable(v, 0));
        }
        return res;
    }

    public static List<VendaRes> entityToRes(List<Venda> vendas){
        return vendas.stream().map(VendaMapper::entityToRes).toList();
    }



    public static VendaDetalhamentoRes entityToResDetalhamento(Venda v, List<ProdutoVendaDetalhamentoRes> produtosDetalhados) {
        Integer id = v.getId();
        LocalDate data = v.getDataHora().toLocalDate();
        LocalTime hora = v.getDataHora().toLocalTime();
        String nomeVendedor = v.getUsuario() != null ? v.getUsuario().getNome() : "---";
        String tipoVenda = v.getTipoVenda().getTipo().getTipo();
        Double descontoTipoVenda = v.getTipoVenda().getDesconto();
        Integer qtdItens = produtosDetalhados.stream().mapToInt(ProdutoVendaDetalhamentoRes::qtd).sum();
        Double valorBruto = produtosDetalhados.stream().mapToDouble(ProdutoVendaDetalhamentoRes::totalBruto).sum();
        Double descontoProdutos = produtosDetalhados.stream().mapToDouble(ProdutoVendaDetalhamentoRes::desconto).sum();
        Double valorLiquido = produtosDetalhados.stream().mapToDouble(ProdutoVendaDetalhamentoRes::subtotal).sum();
        Double descontoVenda = v.getDesconto();
        Double valorTotal = v.getValorTotal();
        String statusVenda = v.getStatusVenda().getStatus().getDescricao();


        VendaDetalhamentoRes dto = new VendaDetalhamentoRes(
            id, data, hora, nomeVendedor, tipoVenda, descontoTipoVenda, qtdItens, valorBruto, descontoProdutos, valorLiquido, descontoVenda, valorTotal, statusVenda, produtosDetalhados
        );
        return dto;
    }


}
