package com.goup.dtos.estoque;

import com.goup.entities.estoque.ETP;

import java.util.List;

public class ETPMapper {
    public static ETPTableRes toTableResponse(ETP req) {
    return new ETPTableRes(
            req.getProduto().getModelo().getCodigo(),
            req.getProduto().getNome(),
            req.getProduto().getModelo().getNome(),
            req.getProduto().getValorRevenda(),
            req.getTamanho().getNumero(),
            req.getProduto().getCor().getNome(),
            req.getLoja().getNome(),
            req.getQuantidade());
    }

    public static  List<ETPTableRes> toTableResponse(List<ETP> req) {
        return req.stream().map(ETPMapper::toTableResponse).toList();
    }
}
