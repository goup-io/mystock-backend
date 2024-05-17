package com.goup.dtos.loja;


public record LojaReq(String nome, String cnpj, String cep, Integer numero, String complemento) {
}