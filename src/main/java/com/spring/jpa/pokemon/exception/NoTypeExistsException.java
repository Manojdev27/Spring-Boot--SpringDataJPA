package com.spring.jpa.pokemon.exception;

import java.io.Serial;

public class NoTypeExistsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public NoTypeExistsException(final String message){
        super(message);
    }

}
