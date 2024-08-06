package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.exception.NoPokemonExistsException;
import com.spring.jpa.pokemon.exception.NoTypeExistsException;
import com.spring.jpa.pokemon.exception.PokemonAlreadyExistsException;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import com.spring.jpa.pokemon.repository.Type1Repository;
import com.spring.jpa.pokemon.repository.Type2Repository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
public class PokemonServiceImpl implements PokemonService {

    private static final Logger logger = LogManager.getLogger(PokemonServiceImpl.class);
    @Autowired
    private final PokemonRepository pokemonRepository;

    @Autowired
    private final Type1Repository type1Repository;

    @Autowired
    private final Type2Repository type2Repository;

    @Override
    public Pokemon saveOnePokemon(Pokemon pokemon) {
       if(pokemonRepository.existsById(pokemon.getPokemonId())){
          throw new PokemonAlreadyExistsException
                   (String.format("There already exists a pokemon with Pokemon id=%s",pokemon.getPokeDexNumber()));
       }

        return pokemonRepository.save(pokemon);
    }

    @Override
    @Transactional
    public List<Pokemon> savePokemon(List<Pokemon> pokemon){
        for(Pokemon pokemon1:pokemon){
            if(pokemonRepository.existsById(pokemon1.getPokemonId())) {
                throw new PokemonAlreadyExistsException(
                        String.format("Pokemon already available for Pokemon id=%s", pokemon1.getPokeDexNumber()));
            }
        }
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
    public List<Pokemon> findAllPokemonById(List<Integer> id) {
        return pokemonRepository.findAllById(id);
    }

    @Override
    public Pokemon findPokemonByName(String name) {
        return pokemonRepository.findPokemonByName(name);
    }


    @Override
    @Transactional
    public Pokemon updatePokemonOnlyType1(@NotNull @Valid final int pokemonId, @NotNull @Valid final int type1Id, @NotNull @Valid final Pokemon pokemon) {
        logger.info("Updating {} on type1id={} pokemonId={}",pokemon,type1Id,pokemonId);
        Type1 type1 = type1Repository.findById(type1Id)
                .orElseThrow(() -> new NoTypeExistsException(String.format("No Type exists with id=%d",type1Id)));

        if (!pokemonRepository.existsById(pokemonId)){
            throw new NoPokemonExistsException(String.format("No Pokemon exists with id=%d",pokemonId));
        }

        pokemon.setPokemonId(pokemonId);
        pokemon.setType1(type1);
        return pokemonRepository.save(pokemon);
    }


    @Override
    @Transactional
    public Pokemon updatePokemon(@NotNull @Valid final int pokemonId, @NotNull @Valid final int type1Id, @Null int type2Id, Pokemon pokemon){
        logger.info("Updating {} on type1id={} type2id={} pokemonId={}",pokemon,type1Id,type2Id,pokemonId);
        Type1 type1 = type1Repository.findById(type1Id)
                .orElseThrow(() -> new NoTypeExistsException(String.format("No Type exists with id=%d",type1Id)));

        Type2 type2 = type2Repository.findById(type2Id)
                .orElseThrow(() -> new NoTypeExistsException(String.format("No Type exists with id=%d",type2Id)));

        if (!pokemonRepository.existsById(pokemonId)){
            throw new NoPokemonExistsException(String.format("No Pokemon exists with id=%d",pokemonId));
        }

        pokemon.setPokemonId(pokemonId);
        pokemon.setType1(type1);
        pokemon.setType2(type2);
        return pokemonRepository.save(pokemon);
   }

    @Override
    @Transactional
    public void deletePokemon(@NotNull @Valid final int pokemonId, @NotNull @Valid final int type1Id) {
        logger.info("Deleting {} on PokemonId={}",type1Id,pokemonId);
        if(!type1Repository.existsById(type1Id)){
            throw new NoTypeExistsException(String.format("No type exists with id=%d",type1Id));
        }
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if(pokemon.isEmpty()){
            throw new NoPokemonExistsException(String.format("No Pokemon exists with id=%d",pokemonId));
        }
pokemonRepository.deleteById(pokemonId);
    }

    @Override
    public void deleteAllPokemon() {
        pokemonRepository.deleteAll();
    }



}
