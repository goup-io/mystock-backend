package com.goup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class BuscaRetornaVazioException extends RuntimeException{
    public BuscaRetornaVazioException(String message) {
        super(message);
    }
}
