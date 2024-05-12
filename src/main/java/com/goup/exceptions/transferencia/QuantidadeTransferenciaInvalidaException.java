package com.goup.exceptions.transferencia;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeTransferenciaInvalidaException extends RuntimeException{
    public QuantidadeTransferenciaInvalidaException(String message){
        super(message);
    }
}
