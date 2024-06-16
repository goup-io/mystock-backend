package com.goup.dtos.estoque.produtos;

import com.goup.dtos.estoque.produtos.modelos.ModeloRes;
import com.goup.entities.estoque.produtos.Cor;
import com.goup.entities.estoque.produtos.Produto;
import com.goup.entities.estoque.produtos.modelos.Modelo;

import java.util.List;

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

    public static ProdutoRes entityToRes(Produto produto){
        int id = produto.getId();
        String cor = produto.getCor().getNome();
        String modelo = produto.getModelo().getNome();
        String nome = produto.getNome();
        double valorCusto = produto.getValorCusto();
        double valorRevenda = produto.getValorRevenda();
        ProdutoRes respostaDto = new ProdutoRes(id, cor, modelo, nome, valorCusto, valorRevenda);
        return respostaDto;
    }

    public static List<ProdutoRes> listToListRes(List<Produto> lista){
        return lista.stream().map(ProdutoMapper::entityToRes).toList();
    }
}
