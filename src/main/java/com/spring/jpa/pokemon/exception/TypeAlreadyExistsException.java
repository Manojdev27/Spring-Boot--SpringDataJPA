package com.spring.jpa.pokemon.exception;

import java.io.Serial;

public class TypeAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public TypeAlreadyExistsException(final String message){
        super(message);
    }
}
