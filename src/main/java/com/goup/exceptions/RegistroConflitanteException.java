package com.goup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegistroConflitanteException extends RuntimeException{
    public RegistroConflitanteException(String message) {
        super(message);
    }
}
