package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.exception.NoTypeExistsException;
import com.spring.jpa.pokemon.exception.TypeAlreadyExistsException;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import com.spring.jpa.pokemon.repository.Type2Repository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Type2ServiceImpl implements Type2Service {

    private static final Logger logger = LogManager.getLogger(Type2ServiceImpl.class);
    @Autowired
    private final Type2Repository type2Repository;

    @Autowired
    private final PokemonRepository pokemonRepository;

    @Override
    @Transactional
    public Type2 saveOneType2(@NotNull @Valid final Type2 type2) {
        if(type2Repository.existsById(type2.getTypeId())){
            throw  new TypeAlreadyExistsException(
                    String.format("Type Already exists with id=%d",type2.getTypeId()));
        }

        return type2Repository.save(type2);
    }

    @Override
    @Transactional
    public void saveType1(@NotNull @Valid final List<Type2> type2){
        for (Type2 type21:type2){
            if(type2Repository.existsById(type21.getTypeId())){
                throw new TypeAlreadyExistsException(
                        String.format("Type is already available for Type id=%s",type21.getTypeId()));
            }
        }
        type2Repository.saveAll(type2);
    }

    @Override
    @Transactional
    public List<Type2> getAllTypes(){
        return type2Repository.findAll();
    }

    @Override
    @Transactional
    public Type2 findOneType(@NotNull @Valid final int id){
        Optional<Type2> optionalType2 =type2Repository.findById(id);
        return optionalType2.orElse(null);

    }

    @Override
    public Type2 findType2ByName(String name) {
        return type2Repository.findType2ByName(name);
    }


    @Override
    @Transactional
    public Type2 updateType2(@NotNull @Valid final int type2Id, @NotNull @Valid final List<Integer> pokemonId, @NotNull @Valid final Type2 type2){
       logger.debug("Updating {} on type2Id={} pokemonId={}",type2,type2Id,pokemonId);
        Type2 type21 = type2Repository.findById(type2Id)
                .orElseThrow(() -> new NoTypeExistsException(String.format("No Type exists with id=%d",type2Id)));

       List<Pokemon> pokemonList = pokemonRepository.findAllById(pokemonId);
      for (Pokemon pokemon1:pokemonList){
          pokemon1.setType2(type21);
          type21.setPokemon(pokemonList);
          pokemonRepository.save(pokemon1);
      }

       return type2Repository.save(type2);

    }

    @Override
    @Transactional
    public void delete(@NotNull @Valid final int type2id) {
        logger.debug("Deleting {}",type2id);
        Optional<Type2> optionalType2 = type2Repository.findById(type2id);
        if (optionalType2.isEmpty()){
            throw new NoTypeExistsException(
                    String.format("No Type exist with id=%s",type2id));
        }
        type2Repository.deleteById(type2id);
    }

    @Override
    @Transactional
    public void deleteAllTypes() {
        type2Repository.deleteAll();
    }

    @Override
    public Object[] countOfType2() {
        return type2Repository.countOfType2();
    }
}
