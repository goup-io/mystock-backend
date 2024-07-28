package com.goup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginInvalidoException extends RuntimeException {
    public LoginInvalidoException(String message) {
        super(message);
    }
}
