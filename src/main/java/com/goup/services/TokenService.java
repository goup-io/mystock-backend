package com.goup.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.goup.entities.usuarios.Login;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String gerarToken(Login usuario) {
        // chave base para encriptação
        Algorithm algorithm = Algorithm.HMAC256("bananinhaxptors");

        return JWT.create()
                .withIssuer("LoginDTO")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5000L))
                .sign(algorithm);

    }
}
