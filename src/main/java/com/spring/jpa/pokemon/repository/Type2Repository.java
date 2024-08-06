package com.spring.jpa.pokemon.repository;

import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Type2Repository extends JpaRepository<Type2,Integer> {
    @Query(value = "select * from type2 where type2 =:name",nativeQuery = true)
    public Type2 findType2ByName(String name);

    @Query(value = "select t2.type2,count(p.pokemon_name) as pokemon_count from pokemon p JOIN Type2 t2 ON t2.type_id = p.type2 GROUP BY t2.type2",nativeQuery = true)
    public Object[] countOfType2();
}
