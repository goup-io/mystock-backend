package com.goup.dtos.estoque;

import com.goup.entities.estoque.ETP;
import com.goup.utils.ListaGenerica;

import java.util.ArrayList;
import java.util.List;

public class ETPMapper {
    public static ETPTableRes toTableResponseEntity(ETP req) {
    return new ETPTableRes(
            req.getId(),
            req.getProduto().getModelo().getCodigo(),
            req.getProduto().getNome(),
            req.getProduto().getModelo().getNome(),
            req.getTamanho().getNumero(),
            req.getProduto().getCor().getNome(),
            req.getProduto().getValorRevenda(),
            req.getLoja().getNome(),
            req.getQuantidade());
    }
    public static ETPEditModal toEditModalEntity(ETP req) {
        return new ETPEditModal(
                req.getId(),
                req.getProduto().getModelo().getCodigo(),
                req.getProduto().getNome(),
                req.getProduto().getModelo().getNome(),
                req.getTamanho().getNumero(),
                req.getProduto().getCor().getNome(),
                req.getProduto().getValorCusto(),
                req.getProduto().getValorRevenda(),
                req.getLoja().getId(),
                req.getProduto().getId());
    }

    public static  List<ETPTableRes> toTableResponse(List<ETP> req) {
        return req.stream().map(ETPMapper::toTableResponseEntity).toList();
    }

    public static List<ETPTableRes> toTableResponse(ListaGenerica<ETP> req) {
        List<ETPTableRes> res = new ArrayList<>();
        for (int i = 0; i < req.getTamanho(); i++) {
            res.add(toTableResponseEntity(req.getElemento(i)));
        }
        return res;
    }
}
