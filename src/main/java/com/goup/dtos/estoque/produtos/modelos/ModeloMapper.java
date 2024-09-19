package com.goup.dtos.estoque.produtos.modelos;

import com.goup.entities.estoque.produtos.modelos.Categoria;
import com.goup.entities.estoque.produtos.modelos.Modelo;
import com.goup.entities.estoque.produtos.modelos.Tipo;

import java.util.List;

public class ModeloMapper {
    public static Modelo reqToEntity(ModeloReq modelo, Categoria categoria, Tipo tipo){
        Modelo entidade = new Modelo();
        entidade.setNome(modelo.nome());
        entidade.setCategoria(categoria);
        entidade.setTipo(tipo);
        return entidade;
    }

    public static List<ModeloRes> listToListRes(List<Modelo> lista){
        return lista.stream().map(ModeloMapper::entityToRes).toList();
    }

    public static ModeloRes entityToRes(Modelo modelo){
        int id = modelo.getId();
        String nome = modelo.getNome();
        String categoria = modelo.getCategoria().getNome();
        String tipo = modelo.getTipo().getNome();
        ModeloRes respostaDto = new ModeloRes(id, nome, categoria, tipo);
        return respostaDto;
    }

    public static Modelo updateEntity(Modelo modelo, ModeloReq atualizacao, Categoria categoria, Tipo tipo){
        modelo.setNome(atualizacao.nome());
        modelo.setCategoria(categoria);
        modelo.setTipo(tipo);
        return modelo;
    }
}
