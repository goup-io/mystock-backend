package com.goup.exceptions.produto.modelo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ModeloComProdutoException extends RuntimeException {
    public ModeloComProdutoException(String message) {
        super(message);
    }
}
