package com.mercado.libre.magneto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ADNIncorrectoException extends RuntimeException{

    public ADNIncorrectoException(final String message) {
        super(message);
    }
}