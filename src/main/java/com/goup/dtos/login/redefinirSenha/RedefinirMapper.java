package com.goup.dtos.login.redefinirSenha;

import com.goup.entities.usuarios.login.RedefinirSenha;

public class RedefinirMapper {
    public static RedefinirSenha redefinirReqToEntity(RedefinirReqDto redefinirReqDto) {
        RedefinirSenha redefinirSenha = new RedefinirSenha();
        redefinirSenha.setLogin(redefinirReqDto.login());
        redefinirSenha.setToken(redefinirReqDto.token());
        return redefinirSenha;
    }
}
