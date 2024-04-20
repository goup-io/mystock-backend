package com.goup.dtos.usuario;

import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;

public record UsuarioResponseDto(Integer codigoVenda, String nome, String email, String telefone, Loja loja, Cargo cargo) {
}
