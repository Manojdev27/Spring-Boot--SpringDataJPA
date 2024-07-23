package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.dto.PokemonResponse;
import com.spring.jpa.pokemon.exception.NoPokemonExistsException;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import com.spring.jpa.pokemon.repository.Type1Repository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Validated
public class PokemonServiceImpl implements PokemonServices{

    private static final Logger logger = LogManager.getLogger(PokemonServiceImpl.class);
    @Autowired
    private final PokemonRepository pokemonRepository;

    @Autowired
    private final Type1Repository type1Repository;

    @Override
    @Transactional
    public List<Pokemon> savePokemon(List<Pokemon> pokemon){
        pokemonRepository.saveAll(pokemon);
        return pokemon;
    }

    @Override
    @Transactional
    public List<Pokemon> getAllPokemon(){
logger.debug("Retrieving the list of all users");
return pokemonRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pokemon> findOnePokemon(int id){
        logger.debug("Retrieving a user by user id= {}", id);

        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        if (pokemon.isEmpty()){
            throw new NoPokemonExistsException(
                    String.format("No user exist with id=%d",id));
        }
        return pokemon;
    }

    @Override
    @Transactional
    public Pokemon updatePokemon(@NotNull @Valid final Pokemon pokemon){
        logger.debug("Updating {}", pokemon);
        Optional<Pokemon> existingPokemon = pokemonRepository.findById(pokemon.getPokemonId());
        if (existingPokemon.isEmpty()){
            throw new NoPokemonExistsException(
                    String.format("No user exist with id=%s",pokemon.getPokemonId()));
        }
        return pokemonRepository.save(pokemon);
   }

    @Override
    @Transactional
    public void deletePokemon(@NotNull @Valid final int pokemonId) {
       logger.debug("Deleting {}",pokemonId);
       Optional<Pokemon> pokemonExisting = pokemonRepository.findById(pokemonId);
        if (pokemonExisting.isEmpty()){
            throw new NoPokemonExistsException(
                    String.format("No user exist with id=%s",pokemonId));
        }
        pokemonRepository.deleteById(pokemonId);
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
