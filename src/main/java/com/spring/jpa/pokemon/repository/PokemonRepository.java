package com.spring.jpa.pokemon.repository;

import com.spring.jpa.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {

@Query(value = "select * from pokemon where pokemon_name =:name",nativeQuery = true)
public Pokemon findPokemonByName(String name);

}
