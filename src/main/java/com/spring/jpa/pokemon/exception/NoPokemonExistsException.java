package com.spring.jpa.pokemon.exception;

public class NoPokemonExistsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoPokemonExistsException(final String message){
        super(message);
    }

}
