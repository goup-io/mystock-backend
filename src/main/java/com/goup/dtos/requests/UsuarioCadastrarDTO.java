package com.goup.dtos.requests;

import com.goup.entities.cargos.Cargo;

public record UsuarioCadastrarDTO(Integer codigoVenda, String nome, Cargo cargo, String telefone) {
}
