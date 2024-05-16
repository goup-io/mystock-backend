package com.goup.services.login;

import com.goup.entities.usuarios.login.Login;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    private LoginLojaRepository lojaLoginRepository;

    @Autowired
    private static LoginRepository usuarioLoginrepository;

    public LoginService(LoginRepository usuarioLoginrepository) {
        this.usuarioLoginrepository = usuarioLoginrepository;
    }

    // verifica qual o tipo de login que o usuário está tentando acessar (loginLoja ou loginUsuario) baseado no seu username
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

    // Retorna qual é o username do usuário baseado na sua PK Composta. Caso não encontre nenhum, retorna "---"
    public static String encontraUsername(Integer usuarioId){
        Login loginById = usuarioLoginrepository.findLoginByIdUsuario(usuarioId);
        if (loginById != null) {
            return loginById.getUsername();
        }
        return "---";
    }
}
