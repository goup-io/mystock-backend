package com.goup.dtos.estoque;

import com.goup.entities.vendas.ItemPromocional;

public record ETPReq(Integer idProduto, Integer idLoja, Integer idTamanho, String codigo, ItemPromocional itemPromocional){
}
