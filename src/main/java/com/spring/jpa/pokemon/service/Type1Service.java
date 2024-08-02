package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.model.Type1;

import java.util.List;

public interface Type1Service {

    public void saveType1(List<Type1> type1);
    public List<Type1> getAllTypes();
    public Type1 findOneType(int id);
    public Type1 updateType(List<Integer> pokemonId, int type1Id, Type1 type1);
//    public Type1 updateType(List<Integer> pokemonId, List<Integer> type1Id, List<Type1> type1);
    public void delete(int type1id);




}
