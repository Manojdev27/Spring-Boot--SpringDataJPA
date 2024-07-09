package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.dto.PokemonResponse;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;
    public void savePokemon(List<Pokemon> pokemon){
        pokemonRepository.saveAll(pokemon);
    }

    public List<PokemonResponse> getAllPokemon(){
List<Pokemon> pokemon2 = pokemonRepository.findAll();
        return pokemon2.stream().map(this::pokemonResponse).collect(Collectors.toList());
    }

    public Pokemon findOnePokemon(int id){
        Optional<Pokemon> optionalPokemon =pokemonRepository.findById(id);
        return optionalPokemon.orElse(null);

    }

    public void updatePokemon(List<Pokemon> pokemon){
                pokemonRepository.saveAll(pokemon);

    }

public PokemonResponse pokemonResponse(Pokemon pokemon){
return PokemonResponse.builder()
        .pokemonId(pokemon.getPokemonId())
        .pokeDexNumber(pokemon.getPokeDexNumber())
        .pokemonName(pokemon.getPokemonName())
        .type1(pokemon.getType1())
        .type2(pokemon.getType2())
        .build();
}
}
