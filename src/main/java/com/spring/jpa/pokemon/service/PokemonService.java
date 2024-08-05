package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PokemonService {
    public Pokemon saveOnePokemon(Pokemon pokemon);
    public List<Pokemon> savePokemon(List<Pokemon> pokemon);
    public List<Pokemon> getAllPokemon();
    public Optional<Pokemon> findOnePokemon(int id);
    public List<Pokemon> findAllPokemonById(List<Integer> id);

    public List<Pokemon> pokemonCountForType1();
    public Pokemon updatePokemonOnlyType1(int pokemonId,int type1Id,Pokemon pokemon);
    public Pokemon updatePokemon(int pokemonId,int type1Id,int type2Id,Pokemon pokemon);

    public void deletePokemon(int pokemonId, int type1Id);
    public void deleteAllPokemon();




}
