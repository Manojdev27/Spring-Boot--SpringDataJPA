package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.Type2;
import com.spring.jpa.pokemon.repository.Type2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Type2Service {
    @Autowired
    private final Type2Repository type2Repository;

    public void saveType1(List<Type2> type2){
        type2Repository.saveAll(type2);
    }

    public List<Type2> getAllTypes(){
        return type2Repository.findAll();
    }

    public Type2 findOneType(int id){
        Optional<Type2> optionalType2 =type2Repository.findById(id);
        return optionalType2.orElse(null);

    }

}
