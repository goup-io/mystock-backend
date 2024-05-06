package com.goup.dtos.estoque.produtos;

import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;

public class ProdutoMapper {
    public static Produto reqToEntity(ProdutoReq produto, Cor cor, Modelo modelo){
        Produto entidade = new Produto();
        entidade.setNome(produto.nome());
        entidade.setCor(cor);
        entidade.setModelo(modelo);
        entidade.setValorCusto(produto.valorCusto());
        entidade.setValorRevenda(produto.valorRevenda());
        return entidade;
    }

    public static Produto updateEntity(Produto produto, Cor cor, Modelo modelo){
        return new Produto();
    }
}
