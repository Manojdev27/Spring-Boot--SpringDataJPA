package com.spring.jpa.pokemon.controller;


import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.repository.PokemonRepository;
import com.spring.jpa.pokemon.repository.Type1Repository;
import com.spring.jpa.pokemon.service.Type1ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/addMultipleTypes")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultiplePokemon(@RequestBody List<Type1> type1List){
        type1Service.saveType1(type1List);
    }
    @GetMapping("/findAllTypes")
    @ResponseStatus(HttpStatus.OK)
    public List<Type1> getAllPokemon(){

        return type1Service.getAllTypes();
    }

    @GetMapping("/findOneType/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Type1> getSingleType(@PathVariable("id") int id){
        Optional<Type1> optionalType1 = Optional.ofNullable(type1Service.findOneType(id));
        return optionalType1.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/updateType/{pokemonId}/{type1Id}")
    public ResponseEntity<Type1> updateType1Pokemon(@PathVariable List<Integer> pokemonId, @PathVariable int type1Id, @RequestBody Type1 type1){
        type1.setTypeId(type1Id);
        return new ResponseEntity<Type1>(type1Service.updateType(pokemonId,type1Id,type1),HttpStatus.OK);
    }

//    @PutMapping("/updateType/{pokemonId}/{type1Id}")
//    public ResponseEntity<Type1> updateType1PokemonBulk(@PathVariable List<Integer> pokemonId, @PathVariable List<Integer> type1Id, @RequestBody List<Type1> type1){
//        return new ResponseEntity<Type1>(type1Service.updateType(pokemonId,type1Id,type1),HttpStatus.OK);
//    }


    @DeleteMapping("/deleteType/{type1Id}")
    public String deleteTypeById(@PathVariable int type1Id){
        type1Service.delete(type1Id);
        return type1Id + " is Deleted";
    }

}
