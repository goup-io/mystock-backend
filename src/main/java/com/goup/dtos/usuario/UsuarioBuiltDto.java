package com.goup.dtos.usuario;

import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;

public record UsuarioBuiltDto(String nome, Cargo cargo, String email, String telefone, Loja loja) {
}
