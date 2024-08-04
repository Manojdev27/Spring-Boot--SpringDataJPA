package com.spring.jpa.pokemon.exception;

import java.io.Serial;

public class NoStatsAvailableException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NoStatsAvailableException(final String message){
        super(message);
    }
}
