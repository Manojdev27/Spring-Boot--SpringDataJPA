package com.spring.jpa.pokemon.controller;

import com.spring.jpa.pokemon.dto.BaseStatsResponse;
import com.spring.jpa.pokemon.dto.PokemonResponse;
import com.spring.jpa.pokemon.model.BaseStats;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.service.BaseStatsServiceImpl;
import com.spring.jpa.pokemon.service.PokemonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class BaseStatsController {

    @Autowired
    private final BaseStatsServiceImpl baseStatsService;

    @Autowired
    private ModelMapper modelmapper;

    private static final Logger logger = LogManager.getLogger(BaseStatsController.class);

    @PostMapping("/addOneStats")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseStats saveBaseStats(@RequestBody BaseStats baseStats){
        logger.info("Received request to create the {}", baseStats);
        return baseStatsService.createOneStats(baseStats);
    }

    @PostMapping("/addStats")
    @ResponseStatus(HttpStatus.CREATED)
    public List<BaseStats> saveMultipleBaseStats(@RequestBody List<BaseStats> baseStats){
        logger.info("Received request to create the {}", baseStats);
        return baseStatsService.createStats(baseStats);
    }

    @GetMapping("/findAllStats")
    @ResponseStatus(HttpStatus.OK)
    public List<BaseStatsResponse> getAllBaseStats(){
        logger.info("Received request to list all users");
        List<BaseStats> baseStats = new ArrayList<>();
        baseStats = (List<BaseStats>) baseStatsService.getAllStats();
        List<BaseStatsResponse> baseStatsResponse = new ArrayList<BaseStatsResponse>();

        for (BaseStats baseStats1 :baseStats){
            baseStatsResponse.add(modelmapper.map(baseStats1,BaseStatsResponse.class));
        }
        return baseStatsResponse;

    }
    @GetMapping("/findOneStats/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<BaseStats> getSingleBaseStats(@PathVariable("id") int id) {
        return Optional.ofNullable(baseStatsService.findOneStats(id));
    }

    @PutMapping("/updateBaseStats/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseStats updateBaseStats(@PathVariable("id") int id,@RequestBody BaseStats baseStats){
        baseStats.setPokeDexNumber(id);
        return baseStatsService.updateStats(baseStats);
    }

    @DeleteMapping("/deleteStats/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStats(@PathVariable int id){
        baseStatsService.deleteStats(id);
        return id + "BaseStats is deleted";
    }

    @DeleteMapping("/deleteAllStats")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllStats(){
        baseStatsService.deleteAllStats();
        return "BaseStats are deleted";
    }

}
