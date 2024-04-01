package com.goup.controllers;

import com.goup.dtos.requests.LoginDTO;
import com.goup.dtos.requests.RegisterDTO;
import com.goup.entities.usuarios.Login;
import com.goup.repositories.LoginRepository;
import com.goup.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken userAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.user(), loginDTO.senha());
        Authentication authenticate = this.authenticationManager.authenticate(userAuthToken);

        var usuario = (com.goup.entities.usuarios.Login) authenticate.getPrincipal();

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if (this.repository.findByUser(registerDTO.user()) != null) return ResponseEntity.status(409).build();

        String senhaEcrypted = new BCryptPasswordEncoder().encode(registerDTO.senha());
        Login novoLogin = new Login(registerDTO.user(), senhaEcrypted, registerDTO.role());

        this.repository.save(novoLogin);

        return ResponseEntity.status(201).build();

    }
}
