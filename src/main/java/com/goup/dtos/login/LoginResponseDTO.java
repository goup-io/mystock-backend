package com.goup.dtos.login;

import com.goup.entities.usuarios.login.UserRole;

public record LoginResponseDTO(String token, Integer idUser, UserRole role){
}
