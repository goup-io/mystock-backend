package com.goup.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.login.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UserDetails userDetails) {
        if (userDetails instanceof Login) {
            return gerarToken((Login) userDetails);
        } else if (userDetails instanceof LojaLogin) {
            return gerarToken((LojaLogin) userDetails);
        } else {
            return null;
        }
    }

    public String gerarToken(Login usuario) {
        // chave base para encriptação
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try{
            String token = JWT.create()
                    .withIssuer("authentication")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch(JWTCreationException exception){
            throw new RuntimeException("Error while generating new token", exception);
        }
    }

    public String gerarToken(LojaLogin usuario) {
        // chave base para encriptação
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try{
            String token = JWT.create()
                    .withIssuer("authentication")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
            return token;
        } catch(JWTCreationException exception){
            throw new RuntimeException("Error while generating new token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("authentication")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            throw new RuntimeException(exception);
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }

}
