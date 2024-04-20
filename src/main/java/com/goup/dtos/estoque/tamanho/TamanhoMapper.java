package com.goup.dtos.estoque.tamanho;

import com.goup.entities.estoque.Tamanho;

public class TamanhoMapper {
    public static Tamanho reqToEntity(TamanhoReq tamanho){
        Tamanho entidade = new Tamanho();
        entidade.setNumero(tamanho.numero());
        return entidade;
    }
}
