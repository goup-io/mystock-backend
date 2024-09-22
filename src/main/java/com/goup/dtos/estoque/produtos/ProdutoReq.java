package com.goup.dtos.estoque.produtos;

import com.goup.entities.vendas.ItemPromocional;

public record ProdutoReq(String codigo, int idCor, int idModelo, String nome, double valorCusto, double valorRevenda, Integer tamanho, ItemPromocional itemPromocional, Integer idLoja){}
