package com.spring.jpa.pokemon.service;

import com.spring.jpa.pokemon.exception.NoStatsAvailableException;
import com.spring.jpa.pokemon.exception.StatsAlreadyExistsException;
import com.spring.jpa.pokemon.model.BaseStats;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.repository.BaseStatsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class BaseStatsServiceImpl implements BaseStatsService{

    private static final Logger logger = LogManager.getLogger(PokemonServiceImpl.class);
    @Autowired
    private final BaseStatsRepository baseStatsRepository;

    @Override
    @Transactional
    public BaseStats createOneStats(BaseStats baseStats) {
        if(baseStatsRepository.existsById(baseStats.getPokeDexNumber())){
           throw new StatsAlreadyExistsException(
                   String.format("Stats already available for Pokemon id=%s",baseStats.getPokeDexNumber()));
        }

        return baseStatsRepository.save(baseStats);
    }

    @Override
    @Transactional
    public List<BaseStats> createStats(List<BaseStats> baseStats) {

        for (BaseStats baseStats1 : baseStats){
            if(baseStatsRepository.existsById(baseStats1.getPokeDexNumber())) {
                throw new StatsAlreadyExistsException(
                        String.format("Stats already available for Pokemon id=%s", baseStats1.getPokeDexNumber()));
            }
        }
        baseStatsRepository.saveAll(baseStats);
        return baseStats;
    }

    @Override
    @Transactional
    public List<BaseStats> getAllStats() {
        logger.debug("Retrieving the list of stats of pokemon");
        return baseStatsRepository.findAll();
    }

    @Override
    @Transactional
    public BaseStats findOneStats(int id) {
        logger.debug("Retrieving stats of One pokemon by id={}",id);
        return baseStatsRepository.findById(id)
                .orElseThrow(() -> new NoStatsAvailableException(String.format("No Stats available for Pokemon id=%s",id)));
    }

    @Override
    @Transactional
    public BaseStats updateStats(BaseStats baseStats) {
        BaseStats baseStats1 = baseStatsRepository.findById(baseStats.getPokeDexNumber())
                .orElseThrow(()->new NoStatsAvailableException(String.format("No Stats available for Pokemon id=%s",baseStats.getPokeDexNumber())));

                baseStats1.setHp(baseStats.getHp());
                baseStats1.setDefense(baseStats.getDefense());
                baseStats1.setAttack(baseStats.getAttack());
                baseStats1.setSpecialAttack(baseStats.getSpecialAttack());
                baseStats1.setSpecialDefense(baseStats.getSpecialDefense());
                baseStats1.setSpeed(baseStats.getSpeed());
        return baseStatsRepository.save(baseStats);
    }


    @Override
    @Transactional
    public void deleteStats(int id) {
        BaseStats baseStats2 = baseStatsRepository.findById(id)
                .orElseThrow(()->new NoStatsAvailableException(String.format("No Stats available for Pokemon id=%s",id)));

        baseStatsRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void deleteAllStats() {
        baseStatsRepository.deleteAll();
    }


}
