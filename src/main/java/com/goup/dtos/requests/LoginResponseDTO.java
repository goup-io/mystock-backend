package com.goup.dtos.requests;

import com.goup.entities.usuarios.Login;
import com.goup.entities.usuarios.UserRole;
import com.goup.entities.usuarios.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public record LoginResponseDTO(String token, UserDetails login) {
}
