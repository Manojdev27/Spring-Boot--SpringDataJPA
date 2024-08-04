package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.model.BaseStats;
import com.spring.jpa.pokemon.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseStatsService {

    public BaseStats createOneStats(BaseStats baseStats);
    public List<BaseStats> createStats(List<BaseStats> baseStats);

    public List<BaseStats> getAllStats();

    public BaseStats findOneStats(int id);

    public BaseStats updateStats(BaseStats baseStats);

    public void deleteStats(int id);

    public void deleteAllStats();
}
