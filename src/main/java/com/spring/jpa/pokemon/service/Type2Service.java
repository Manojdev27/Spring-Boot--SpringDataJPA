package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;

import java.util.List;

public interface Type2Service {

    public Type2 saveOneType2(Type2 type2);
    public void saveType1(List<Type2> type2);
    public List<Type2> getAllTypes();
    public Type2 findOneType(int id);
    public Type2 updateType2(int type2Id, List<Integer> pokemonId, Type2 type2);

    public void delete(int type2id);

    public void deleteAllTypes();

}
