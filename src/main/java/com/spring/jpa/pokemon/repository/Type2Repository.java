package com.spring.jpa.pokemon.repository;

import com.spring.jpa.pokemon.model.Type2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Type2Repository extends JpaRepository<Type2,Integer> {

}
