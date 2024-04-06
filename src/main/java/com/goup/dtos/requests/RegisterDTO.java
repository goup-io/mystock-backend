package com.goup.dtos.requests;

import com.goup.entities.usuarios.UserRole;

public record RegisterDTO(String user, String senha, UserRole role, Integer userId) { }
