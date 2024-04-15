package com.goup.dtos.login;

import org.springframework.security.core.userdetails.UserDetails;

public record LoginResponseDTO(String token, UserDetails login) {
}
