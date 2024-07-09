package com.spring.jpa.pokemon.dto;

import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponse {

    private int pokemonId;

    private int pokeDexNumber;

    private String pokemonName;

    private Type1 type1;

    private Type2 type2;

}
