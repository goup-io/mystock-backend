package com.goup.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.goup.entities.usuarios.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

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

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("authentication")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception){
            return "";
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }

}
