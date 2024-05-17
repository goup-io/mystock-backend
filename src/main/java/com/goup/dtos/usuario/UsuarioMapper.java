package com.goup.dtos.usuario;

import com.goup.entities.cargos.Cargo;
import com.goup.entities.lojas.Loja;
import com.goup.entities.usuarios.Usuario;
import com.goup.services.login.LoginService;

import java.util.List;
import java.util.Objects;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioBuiltDto usuarioBuiltDto){
        Usuario usuario = new Usuario();
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
                usuarioResponse.getId(),
                usuarioResponse.getCodigoVenda(),
                usuarioResponse.getNome(),
                usuarioResponse.getEmail(),
                usuarioResponse.getTelefone(),
                usuarioResponse.getLoja(),
                usuarioResponse.getCargo());
    }

    public static Usuario toEntityAtualizar(UsuarioAtualizarDto usuarioAtualizarDto, Usuario usuario, Cargo cargo, Loja loja){
        usuario.setNome(usuarioAtualizarDto.nome());
        usuario.setCodigoVenda(usuario.getCodigoVenda());
        usuario.setEmail(usuarioAtualizarDto.email());
        usuario.setTelefone(usuarioAtualizarDto.telefone());
        usuario.setCargo(cargo);
        usuario.setLoja(loja);
        return usuario;
    }

    public static UsuarioResTableDto entityToResTable(Usuario usuario, String usuarioLogin){
        return new UsuarioResTableDto(
                usuario.getId(),
                usuario.getCodigoVenda(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getLoja().getNome(),
                usuario.getCargo().getNome(),
                usuarioLogin
        );
    }

    public static List<UsuarioResTableDto> toListResTable(List<Usuario> usuariosEncontrados) {
        return usuariosEncontrados.stream()
                .map(usuario -> {
                    String username = LoginService.encontraUsername(usuario.getId());
                    return entityToResTable(usuario, username);
                })
                .toList();
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::entityToReponse)
                .toList();
    }


}