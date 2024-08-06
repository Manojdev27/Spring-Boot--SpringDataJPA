package com.spring.jpa.pokemon.controller;


import com.spring.jpa.pokemon.dto.Type1Response;
import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.model.Type2;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import com.spring.jpa.pokemon.repository.Type1Repository;
import com.spring.jpa.pokemon.service.Type1ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type1")
@RequiredArgsConstructor
public class Type1Controller {

@Autowired
private final Type1ServiceImpl type1Service;

@Autowired
private final Type1Repository type1Repository;

@Autowired
private final PokemonRepository pokemonRepository;

    @Autowired
    private final ModelMapper modelMapper;

    private static final Logger logger = LogManager.getLogger(Type1Controller.class);

    @PostMapping("/addOneType")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultipleType(@RequestBody Type1 type1){

        type1Service.saveOneType1(type1);
    }
    @PostMapping("/addMultipleTypes")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultipleType(@RequestBody List<Type1> type1List){

        type1Service.saveType1(type1List);
    }
    @GetMapping("/findAllType1")
    @ResponseStatus(HttpStatus.OK)
    public List<Type1Response> getAllType1(){
        logger.debug("Received request to all list types");
        List<Type1> type1List = new ArrayList<Type1>();
        type1List = type1Service.getAllTypes();
        List<Type1Response> type1Response = new ArrayList<Type1Response>();
        for (Type1 type1:type1List) {
            type1Response.add(modelMapper.map(type1, Type1Response.class));
        }
        return type1Response;
    }

    @GetMapping("/findAllTypesWithPokemon")
    @ResponseStatus(HttpStatus.OK)
    public List<Type1> getAllType1WithPokemon() {
    return type1Service.getAllTypes();
    }

    @GetMapping("/findOneType/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Type1> getSingleType(@PathVariable("id") int id){
        Optional<Type1> optionalType1 = Optional.ofNullable(type1Service.findOneType(id));
        return optionalType1.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/findTypeByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Type1 findTypeByName(@PathVariable("name") String name){

        return type1Service.findOneTypeByName(name);
    }

    @PutMapping("/updateType/{pokemonId}/{type1Id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Type1> updateType1Pokemon(@PathVariable List<Integer> pokemonId, @PathVariable int type1Id, @RequestBody Type1 type1){
        type1.setTypeId(type1Id);
        return new ResponseEntity<Type1>(type1Service.updateType(pokemonId,type1Id,type1),HttpStatus.OK);
    }

//    @PutMapping("/updateType/{pokemonId}/{type1Id}")
//    public ResponseEntity<Type1> updateType1PokemonBulk(@PathVariable List<Integer> pokemonId, @PathVariable List<Integer> type1Id, @RequestBody List<Type1> type1){
//        return new ResponseEntity<Type1>(type1Service.updateType(pokemonId,type1Id,type1),HttpStatus.OK);
//    }


    @DeleteMapping("/deleteType/{type1Id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTypeById(@PathVariable int type1Id){
        type1Service.delete(type1Id);
        return type1Id + " is Deleted";
    }
    @DeleteMapping("/deleteAllType")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllType(){
        return "All type1 are Deleted";
    }

    @GetMapping("/countOfAllType1Pokemon")
    @ResponseStatus(HttpStatus.OK)
    public Object[] countOfType1(){
        return type1Service.countOfType1();


    }
}
