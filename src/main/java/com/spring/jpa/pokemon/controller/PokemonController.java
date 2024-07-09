package com.spring.jpa.pokemon.controller;

import com.spring.jpa.pokemon.dto.PokemonResponse;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    @Autowired
    private final PokemonService pokemonService;

    @PostMapping("/addMultiplePokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMultiplePokemon(@RequestBody List<Pokemon> pokemon){
        pokemonService.savePokemon(pokemon);
    }
    @GetMapping("/findAllPokemon")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonResponse> getAllPokemon(){

        return pokemonService.getAllPokemon();
    }

    @GetMapping("/findOnePokemon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pokemon> getSinglePokemon(@PathVariable("id") int id){
        Optional<Pokemon> pokemon = Optional.ofNullable(pokemonService.findOnePokemon(id));
        return pokemon.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PutMapping("/updatePokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePokemon(@RequestBody List<Pokemon> pokemon){
pokemonService.updatePokemon(pokemon);


    }


}
