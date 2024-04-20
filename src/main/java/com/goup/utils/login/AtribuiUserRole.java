package com.goup.utils.login;

import com.goup.entities.usuarios.Usuario;
import com.goup.entities.usuarios.login.UserRole;

public class AtribuiUserRole{
    public static UserRole atribuiUserRole(Usuario usuario) {
        if (usuario.getCargo().getNome().equals("ADMIN")) {
            return UserRole.ADMIN;
        } else if (usuario.getCargo().getNome().equals("GERENTE")) {
            return UserRole.GERENTE;
        } else {
            return UserRole.VENDEDOR;
        }
    }
}
