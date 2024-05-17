package com.goup.dtos.usuario;

import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;

public record UsuarioResTableDto (Integer id, Integer codigoVenda, String nome, String email, String telefone, String loja, String cargo, String usuario){
}
