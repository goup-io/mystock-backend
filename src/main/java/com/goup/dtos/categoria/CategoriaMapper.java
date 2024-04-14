package com.goup.dtos.categoria;

import com.goup.entities.produtos.modelos.Categoria;

public class CategoriaMapper {
    public static Categoria reqToEntity(CategoriaReq categoria){
        Categoria entidade = new Categoria();
        entidade.setNome(categoria.nome());
        return entidade;
    }
}
