package com.goup.dtos.estoque.produtos.modelos.categoria;

import com.goup.entities.estoque.produtos.modelos.Categoria;

public class CategoriaMapper {
    public static Categoria reqToEntity(CategoriaReq categoria){
        Categoria entidade = new Categoria();
        entidade.setNome(categoria.nome());
        return entidade;
    }
}
