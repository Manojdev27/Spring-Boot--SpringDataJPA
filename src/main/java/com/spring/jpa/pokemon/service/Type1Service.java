package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.repository.Type1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Type1Service {

    @Autowired
    private final Type1Repository type1Repository;

    public void saveType1(List<Type1> type1){
       type1Repository.saveAll(type1);
    }

    public List<Type1> getAllTypes(){
        return type1Repository.findAll();
    }

    public Type1 findOneType(int id){
        Optional<Type1> optionalType1 =type1Repository.findById(id);
        return optionalType1.orElse(null);

    }


}
