package com.goup.dtos.login.redefinirSenha;

import com.goup.entities.usuarios.login.Login;

public record RedefinirReqDto(Login login, String token) {
}
