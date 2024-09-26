package com.goup.security;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.goup.entities.lojas.LojaLogin;
import com.goup.entities.usuarios.login.Login;
import com.goup.entities.usuarios.login.UserRole;
import com.goup.repositories.lojas.LoginLojaRepository;
import com.goup.repositories.usuarios.LoginRepository;
import com.goup.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginLojaRepository loginLojaRepository;

    @Autowired
    private TokenBlacklist tokenBlacklist;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        try {
            if (token != null) {
                if (tokenBlacklist.isBlacklisted(token)) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    SecurityContextHolder.clearContext();
                    return;
                }

                String userName = tokenService.validateToken(token);

                Login loginInfos = (Login) loginRepository.findByUsername(userName) == null ? null : (Login) loginRepository.findByUsername(userName);
                LojaLogin lojaLoginInfos = (LojaLogin) loginLojaRepository.findByUsername(userName) == null ? null : (LojaLogin) loginLojaRepository.findByUsername(userName);

                UsernamePasswordAuthenticationToken authentication = null;

                if (loginInfos != null) {
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_"+UserRole.valueOf(loginInfos.getUsuario().getCargo().getNome().toUpperCase()).toString()));
                    authentication = new UsernamePasswordAuthenticationToken(loginInfos, null, authorities);
                }
                if (lojaLoginInfos != null) {
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_"+lojaLoginInfos.getAcessoLoja().getTipo().toString()));
                    authentication = new UsernamePasswordAuthenticationToken(lojaLoginInfos, null, authorities);
                }

                if (loginInfos == null && lojaLoginInfos == null) {
                    SecurityContextHolder.clearContext();
                    throw new RuntimeException("Usuário não encontrado");
                }
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JWTCreationException e){
            throw new RuntimeException(e);
        } catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Token invalido");
            return;
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
