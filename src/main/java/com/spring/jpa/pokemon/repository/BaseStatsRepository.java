package com.spring.jpa.pokemon.repository;

import com.spring.jpa.pokemon.model.BaseStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStatsRepository extends JpaRepository<BaseStats,Integer> {

}
