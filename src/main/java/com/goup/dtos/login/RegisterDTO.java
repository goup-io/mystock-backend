package com.goup.dtos.login;

import com.goup.entities.cargos.Cargo;

public record RegisterDTO(String user, String senha, Cargo cargo, Integer userId) { }
