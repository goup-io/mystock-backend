package com.goup.dtos.estoque.produtos.cor;

import com.goup.entities.estoque.produtos.Cor;

public class CorMapper {
    public static Cor reqToEntity(CorReq cor){
        Cor entidade = new Cor();
        entidade.setNome(cor.nome());
        return entidade;
    }
}
