package com.spring.jpa.pokemon.controller;


import com.spring.jpa.pokemon.model.Type1;
import com.spring.jpa.pokemon.service.Type1Service;
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
private final Type1Service type1Service;

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

}
