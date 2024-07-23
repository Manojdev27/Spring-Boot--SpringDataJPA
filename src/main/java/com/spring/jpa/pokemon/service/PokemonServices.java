package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.Pokemon;

import java.util.List;
import java.util.Optional;

public interface PokemonServices {

    public List<Pokemon> savePokemon(List<Pokemon> pokemon);
    public List<Pokemon> getAllPokemon();
    public Optional<Pokemon> findOnePokemon(int id);
    public Pokemon updatePokemon(Pokemon pokemon);
    public void deletePokemon(int pokemonId);

}
