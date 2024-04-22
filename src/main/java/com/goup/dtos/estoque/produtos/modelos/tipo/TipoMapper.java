package com.goup.dtos.estoque.produtos.modelos.tipo;

import com.goup.entities.estoque.produtos.modelos.Tipo;

public class TipoMapper {
    public static Tipo reqToEntity(TipoReq tipo){
        Tipo entidade = new Tipo();
        entidade.setNome(tipo.nome());
        return entidade;
    }
}
