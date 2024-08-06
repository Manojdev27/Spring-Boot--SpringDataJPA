package com.spring.jpa.pokemon.controller;

import com.spring.jpa.pokemon.dto.Type2Response;
import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;
import com.spring.jpa.pokemon.service.PokemonServiceImpl;
import com.spring.jpa.pokemon.service.Type2ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type2")
@RequiredArgsConstructor
public class Type2Controller {

    @Autowired
    private final Type2ServiceImpl type2Service;

    @Autowired
    private final ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(Type2Controller.class);

    @PostMapping("/addOneType2")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOneType2(@RequestBody Type2 type2){
        logger.debug("Received request to create the {}",type2);
        type2Service.saveOneType2(type2);
    }
    @PostMapping("/addMultipleType2")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultipleType2(@RequestBody List<Type2> type2List){
        logger.debug("Received request to create the {}",type2List);
        type2Service.saveType1(type2List);
    }

    @GetMapping("/findAllType2")
    @ResponseStatus(HttpStatus.OK)
    public List<Type2Response> getAllType2(){
        logger.debug("Received request to all list types");
        List<Type2> type2List = new ArrayList<Type2>();
        type2List = type2Service.getAllTypes();
        List<Type2Response> type2Responses = new ArrayList<Type2Response>();
        for (Type2 type2:type2List) {
            type2Responses.add(modelMapper.map(type2, Type2Response.class));
        }
        return type2Responses;
    }

    @GetMapping("/findAllType2WithPokemon")
    @ResponseStatus(HttpStatus.OK)
    public List<Type2> getAllType2WithPokemon() {
        return type2Service.getAllTypes();
    }

    @GetMapping("/findTypeByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Type2 findTypeByName(@PathVariable("name") String name){

        return type2Service.findType2ByName(name);
    }
    @GetMapping("/findOneType2/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Type2> getSingleType(@PathVariable("id") int id){
        Optional<Type2> optionalType1 = Optional.ofNullable(type2Service.findOneType(id));
        return optionalType1.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/updatePokemon/{pokemonId}/updateType2/{type2Id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Type2 updateType2WithPokemon(@PathVariable int type2Id, @PathVariable List<Integer> pokemonId, @RequestBody Type2 type2) {

       return type2Service.updateType2(type2Id,pokemonId,type2);
    }

    @DeleteMapping("/deleteType/{type2Id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTypeById(@PathVariable int type2Id){
        type2Service.delete(type2Id);
        return type2Id + " is Deleted";
    }
    @DeleteMapping("/deleteAllType")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllType(){
        return "All type2 are Deleted";
    }

    @GetMapping("/countOfAllType2Pokemon")
    @ResponseStatus(HttpStatus.OK)
    public Object[] countOfType2() {
        return type2Service.countOfType2();
    }
}
