package com.goup.dtos.tamanho;

import com.goup.entities.produtos.Tamanho;

public class TamanhoMapper {
    public static Tamanho reqToEntity(TamanhoReq tamanho){
        Tamanho entidade = new Tamanho();
        entidade.setNumero(tamanho.numero());
        return entidade;
    }
}
