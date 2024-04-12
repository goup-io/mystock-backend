package com.goup.dtos.cor;

import com.goup.entities.produtos.Cor;

public class CorMapper {
    public static Cor reqToEntity(CorReq cor){
        Cor entidade = new Cor();
        entidade.setNome(cor.nome());
        return entidade;
    }
}
