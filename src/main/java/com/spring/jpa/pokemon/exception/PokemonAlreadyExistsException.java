package com.spring.jpa.pokemon.exception;

import java.io.Serial;

public class PokemonAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public PokemonAlreadyExistsException(final String message){
        super(message);
    }
}
