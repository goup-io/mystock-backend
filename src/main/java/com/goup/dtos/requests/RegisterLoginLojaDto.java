package com.goup.dtos.requests;

import com.goup.entities.lojas.TipoLogin;

public record RegisterLoginLojaDto(String user, String senha, Integer acessoLojaId, TipoLogin tipoLogin, Integer userId) {
}
