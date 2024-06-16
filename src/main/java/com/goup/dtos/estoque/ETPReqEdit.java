package com.goup.dtos.estoque;

import com.goup.entities.vendas.ItemPromocional;

public record ETPReqEdit(String nome, Double valorCusto, Double valorRevenda, ItemPromocional itemPromocional) {
}