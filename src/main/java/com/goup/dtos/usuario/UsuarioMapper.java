package com.goup.dtos.usuario;

import com.goup.entities.usuarios.Usuario;

import java.util.List;
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
                usuarioResponse.getLoja(),
                usuarioResponse.getCargo());
    }

    public static Usuario toEntityAtualizar(UsuarioAtualizarDto usuarioAtualizarDto){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioAtualizarDto.nome());
        usuario.setEmail(usuarioAtualizarDto.email());
        usuario.setTelefone(usuarioAtualizarDto.telefone());
        return usuario;
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::entityToReponse)
                .toList();
    }


}