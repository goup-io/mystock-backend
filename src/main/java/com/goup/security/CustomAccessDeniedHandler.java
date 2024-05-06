package com.goup.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.Arrays;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println("{ \"error\": \"Acesso negado: " + accessDeniedException.getMessage() + "\" }");
        response.getOutputStream().println("{ \"error\": \"Acesso negado: " + Arrays.toString(accessDeniedException.getStackTrace()) + "\" }");
        response.getOutputStream().println("{ \"error\": \"Acesso negado: " + accessDeniedException.getCause() + "\" }");
        response.getOutputStream().println("{ \"error\": \"Acesso negado: " + Arrays.toString(accessDeniedException.getSuppressed()) + "\" }");

    }

}