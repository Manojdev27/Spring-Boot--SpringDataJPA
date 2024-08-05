package com.spring.jpa.pokemon.repository;

import com.spring.jpa.pokemon.model.Type1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Type1Repository extends JpaRepository<Type1,Integer> {

    @Query("select t1.type1,count(p.pokemon_name) as pokemon_count from pokemon p JOIN Type1 t1 ON t1.type_id = p.type1 GROUP BY t1.type1")
    public List<Type1> countOfType1(String name);
}
