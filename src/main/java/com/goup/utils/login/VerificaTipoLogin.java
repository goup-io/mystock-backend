package com.goup.utils.login;

import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.login.Login;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class VerificaTipoLogin {

    @Autowired
    private LoginLojaRepository lojaLoginRepository;

    @Autowired
    private LoginRepository usuarioLoginrepository;

    // verifica qual o tipo de login que o usuário está tentando acessar (loginLoja ou loginUsuario)
    public UserDetails verificaTipoLogin(String user) {

        boolean isLoginUser = usuarioLoginrepository.findByUsername(user) != null;

        boolean isLoginLoja = lojaLoginRepository.findByUsername(user) != null;

        if (isLoginUser){
            return usuarioLoginrepository.findByUsername(user);
        }

        if (isLoginLoja){
            return lojaLoginRepository.findByUsername(user);
        }

        throw new RuntimeException("Usuário não encontrado!");
    }
}
