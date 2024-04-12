package com.goup.dtos.tipo;

import com.goup.entities.produtos.modelos.Tipo;

public class TipoMapper {
    public static Tipo reqToEntity(TipoReq tipo){
        Tipo entidade = new Tipo();
        entidade.setNome(tipo.nome());
        return entidade;
    }
}
