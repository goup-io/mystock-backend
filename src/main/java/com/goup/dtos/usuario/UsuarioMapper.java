package com.goup.dtos.usuario;

import com.goup.entities.usuarios.Usuario;

import java.util.Objects;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioBuiltDto usuarioBuiltDto){
        Usuario usuario = new Usuario();
        usuario.setCodigoVenda(0);
        usuario.setNome(usuarioBuiltDto.nome());
        usuario.setCargo(usuarioBuiltDto.cargo());
        usuario.setEmail(usuarioBuiltDto.email());
        usuario.setTelefone(usuarioBuiltDto.telefone());
        usuario.setLoja(usuarioBuiltDto.loja());

        return usuario;
    }

    public static UsuarioResponseDto entityToReponse(Usuario usuarioResponse){
        if (Objects.isNull(usuarioResponse)) {
            return null;
        }
        return new UsuarioResponseDto(
                usuarioResponse.getCodigoVenda(),
                usuarioResponse.getNome(),
                usuarioResponse.getEmail(),
                usuarioResponse.getTelefone(),
                usuarioResponse.getLoja());
    }


}