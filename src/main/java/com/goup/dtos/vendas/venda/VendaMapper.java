package com.goup.dtos.vendas.venda;

import com.goup.entities.usuarios.Usuario;
import com.goup.entities.vendas.StatusVenda;
import com.goup.entities.vendas.TipoVenda;
import com.goup.entities.vendas.Venda;

import java.time.LocalDateTime;
import java.util.List;

public class VendaMapper {
    public static VendaRes entityToRes(Venda venda){
        return new VendaRes(
                venda.getId(),
                venda.getDataHora(),
                venda.getDesconto(),
                venda.getValorTotal(),
                venda.getStatusVenda().getStatus().getDescricao(),
                venda.getTipoVenda().getTipo(),
                venda.getUsuario().getNome()
        );
    }

    public static Venda reqToEntity(VendaReq req, Usuario usuario, TipoVenda tipoVenda){
        Venda venda = new Venda();
        venda.setDataHora(LocalDateTime.now());
        venda.setDesconto(req.desconto());
        venda.setValorTotal(req.valorTotal());
        venda.setTipoVenda(tipoVenda);
        venda.setUsuario(usuario);
        return venda;
    }

    public static List<VendaRes> entityToRes(List<Venda> vendas){
        return vendas.stream().map(VendaMapper::entityToRes).toList();
    }

}
