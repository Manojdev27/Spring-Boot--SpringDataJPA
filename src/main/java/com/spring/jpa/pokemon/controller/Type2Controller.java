package com.spring.jpa.pokemon.controller;

import com.spring.jpa.pokemon.model.Type2;
import com.spring.jpa.pokemon.service.Type2ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type2")
@RequiredArgsConstructor
public class Type2Controller {

    @Autowired
    private final Type2ServiceImpl type2Service;

    @PostMapping("/addMultipleType2")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultiplePokemon(@RequestBody List<Type2> type2List){
        type2Service.saveType1(type2List);
    }
    @GetMapping("/findAllType2")
    @ResponseStatus(HttpStatus.OK)
    public List<Type2> getAllPokemon(){

        return type2Service.getAllTypes();
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



}
