package com.goup.exceptions.produto.modelo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ModeloComETPException extends RuntimeException {
    public ModeloComETPException(String message) {
        super(message);
    }
}