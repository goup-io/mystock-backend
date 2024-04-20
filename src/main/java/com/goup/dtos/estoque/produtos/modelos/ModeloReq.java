package com.goup.dtos.estoque.produtos.modelos;

import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.entities.estoque.produtos.modelos.Tipo;

public record ModeloReq(String codigo, String nome, int idCategoria, int idTipo) {
}
