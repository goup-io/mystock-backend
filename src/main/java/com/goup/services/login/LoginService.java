package com.goup.services.login;

import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.login.Login;
import com.goup.exceptions.LoginInvalidoException;
import com.goup.exceptions.RegistroNaoEncontradoException;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    private LoginLojaRepository lojaLoginRepository;

    @Autowired
    private static LoginRepository usuarioLoginrepository;

    @Autowired
    private PasswordEncoder encoder;

    public LoginService(LoginRepository usuarioLoginrepository) {
        this.usuarioLoginrepository = usuarioLoginrepository;
    }

    // verifica qual o tipo de login que o usuário está tentando acessar (loginLoja ou loginUsuario) baseado no seu username
    public UserDetails verificaTipoLogin(String user) {

        boolean isLoginUser = usuarioLoginrepository.existsByUsername(user);

        boolean isLoginLoja = lojaLoginRepository.existsByUsername(user);

        if (isLoginLoja || isLoginUser){
            return isLoginUser ? usuarioLoginrepository.findByUsername(user) : lojaLoginRepository.findByUsername(user);
        }

        return null;
    }

    public UserDetails buscarLogin(String username){
        Login loginUser = (Login) usuarioLoginrepository.findByUsername(username);
        LojaLogin loginLoja = (LojaLogin) lojaLoginRepository.findByUsername(username);
        if (loginUser != null){
            return loginUser;
        } else if (loginLoja != null) {
            return loginLoja;
        }
        return null;
    }

    public boolean validarLogin(UserDetails acesso, String senha){
        if (acesso == null){
            return false;
        }
        boolean passwordOk = encoder.matches(senha, acesso.getPassword());
        if (!passwordOk){
            return false;
        }
        return true;
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
