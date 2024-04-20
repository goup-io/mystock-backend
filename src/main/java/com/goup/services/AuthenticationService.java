package com.goup.services;

import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.lojas.TipoLogin;
import com.goup.entities.usuarios.login.Login;
import com.goup.entities.usuarios.login.UserRole;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    LoginLojaRepository loginLojaRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario;
        boolean isLoginLoja = loginLojaRepository.findByUsername(username) != null;
        if (isLoginLoja){
            LojaLogin lojaLogin = (LojaLogin) loginLojaRepository.findByUsername(username);
            lojaLogin.setRole(lojaLogin.getAcessoLoja().getTipo());
            usuario = lojaLogin;
        } else {
            Login login = (Login) loginRepository.findByUsername(username);
            login.setRole(UserRole.valueOf(login.getUsuario().getCargo().getNome().toUpperCase()));
            usuario = login;
        }

        return usuario;
    }
}
