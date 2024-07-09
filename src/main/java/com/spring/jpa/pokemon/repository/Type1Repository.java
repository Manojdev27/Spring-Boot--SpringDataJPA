package com.spring.jpa.pokemon.repository;

import com.spring.jpa.pokemon.model.Type1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Type1Repository extends JpaRepository<Type1,Integer> {


}
