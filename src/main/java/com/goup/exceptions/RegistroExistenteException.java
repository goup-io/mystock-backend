package com.goup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RegistroExistenteException extends RuntimeException{
    public RegistroExistenteException(String message) {
        super(message);
    }
}
