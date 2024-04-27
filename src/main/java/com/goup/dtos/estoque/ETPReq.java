package com.goup.dtos.estoque;


import com.goup.multiple_pk.ProdutoPK;

public record ETPReq(ProdutoPK pkProduto, Integer idLoja, Integer tamanho){
}
