package com.mercado.libre.magneto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoMutanteException extends RuntimeException{

    public NoMutanteException(final String message) {
        super(message);
    }
}