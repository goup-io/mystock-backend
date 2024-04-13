package com.goup.security;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.Login;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import com.goup.services.TokenService;
import com.goup.utils.login.VerificaTipoLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    LoginLojaRepository loginLojaRepository;

    @Autowired
    VerificaTipoLogin verificaTipoLogin;

    @Autowired
    private TokenBlacklist tokenBlacklist;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        try {
            if (token != null) {
                if (tokenBlacklist.isBlacklisted(token)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return; // Retorna imediatamente, não prossegue com a autenticação/autorização
                }

                var userName = tokenService.validateToken(token);

                UserDetails user;

                var userDetails = verificaTipoLogin.verificaTipoLogin(userName);
                if (userDetails instanceof Login) {
                    user = loginRepository.findByUser(userName);
                } else if (userDetails instanceof LojaLogin) {
                    user = loginLojaRepository.findByUser(userName);
                } else {
                    throw new IllegalArgumentException("Unsupported user type");
                }

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JWTCreationException e){
            throw new RuntimeException(e);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }



    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null){
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
