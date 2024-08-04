package com.spring.jpa.pokemon.exception;

import java.io.Serial;

public class StatsAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public StatsAlreadyExistsException(final String message){
        super(message);
    }

}
