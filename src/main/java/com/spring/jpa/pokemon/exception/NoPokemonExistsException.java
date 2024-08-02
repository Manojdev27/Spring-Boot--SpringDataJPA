package com.spring.jpa.pokemon.exception;

import java.io.Serial;

public class NoPokemonExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NoPokemonExistsException(final String message){
        super(message);
    }

}
