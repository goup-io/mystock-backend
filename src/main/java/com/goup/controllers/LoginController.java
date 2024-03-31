package com.goup.controllers;

import com.goup.dtos.requests.Login;
import com.goup.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody Login login){
        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(login.user(), login.senha());

        Authentication authenticate = this.authenticationManager.authenticate(userAuthToken);

        var usuario = (com.goup.entities.usuarios.Login) authenticate.getPrincipal();

        return tokenService.gerarToken(usuario);
    }
}
