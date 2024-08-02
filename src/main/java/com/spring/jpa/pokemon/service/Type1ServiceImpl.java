package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.exception.NoPokemonExistsException;
import com.spring.jpa.pokemon.exception.NoTypeExistsException;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import com.spring.jpa.pokemon.repository.Type1Repository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Type1ServiceImpl implements Type1Service{

    private static final Logger logger = LogManager.getLogger(Type1ServiceImpl.class);

    @Autowired
    private final Type1Repository type1Repository;
    @Autowired
    private final PokemonRepository pokemonRepository;

    @Override
    @Transactional
    public void saveType1(List<Type1> type1){
       type1Repository.saveAll(type1);
    }

    @Override
    @Transactional
    public List<Type1> getAllTypes(){
        return type1Repository.findAll();
    }

    @Override
    @Transactional
    public Type1 findOneType(@NotNull @Valid int id){
        Optional<Type1> optionalType1 =type1Repository.findById(id);
        return optionalType1.orElse(null);

    }

    @Override
    @Transactional
    public Type1 updateType(@NotNull @Valid final List<Integer> pokemonId,@NotNull @Valid final int type1Id,@NotNull @Valid final Type1 type1) {
        logger.debug("Updating {} on type1id={} pokemonId={}",type1,type1Id,pokemonId);
        Type1 type11 = type1Repository.findById(type1Id)
                .orElseThrow(() -> new NoTypeExistsException(String.format("No Type exists with id=%d",type1Id)));

        List<Pokemon> pokemonIds = pokemonRepository.findAllById(pokemonId);

        for (Pokemon pokemon:pokemonIds){
           type11.setPokemon(pokemonIds);
           pokemon.setType1(type11);
           pokemonRepository.save(pokemon);
        }
        return type1Repository.save(type1);
    }

//    @Override
//    @Transactional
//    public Type1 updateType(List<Integer> pokemonId, List<Integer> type1Id, List<Type1> type1) {
//        logger.debug("Updating {} on type1id={} pokemonId={}",type1,type1Id,pokemonId);
//        List<Type1> type11 = type1Repository.findAllById(type1Id);
//        List<Pokemon> pokemonIds = pokemonRepository.findAllById(pokemonId);
//
//        for (Type1 type12:type11){
//            type12.setPokemon(pokemonIds);
//        }
//
//        for (Pokemon pokemon:pokemonIds){
//            pokemon.setType1(List.of(type11));
//            pokemonRepository.save(pokemon);
//        }
//         type1Repository.saveAll(type1);
//        return (Type1) type1;
//    }

    @Override
    @Transactional
    public void delete(@NotNull @Valid final int type1id) {

        logger.debug("Deleting {}",type1id);
        Optional<Type1> optionalType1 = type1Repository.findById(type1id);
        if (optionalType1.isEmpty()){
            throw new NoTypeExistsException(
                    String.format("No Type exist with id=%s",type1id));
        }
        type1Repository.deleteById(type1id);
    }



}
