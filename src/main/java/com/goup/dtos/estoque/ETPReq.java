package com.goup.dtos.estoque;


import com.goup.multiple_pk.ProdutoPK;

public record ETPReq(Integer idProduto, Integer idLoja, Integer tamanho){
}
