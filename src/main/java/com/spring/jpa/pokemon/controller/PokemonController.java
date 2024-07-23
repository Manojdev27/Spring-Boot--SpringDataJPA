package com.spring.jpa.pokemon.controller;

import com.spring.jpa.pokemon.dto.PokemonResponse;
import com.spring.jpa.pokemon.model.Pokemon;
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
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    @Autowired
    private final PokemonServiceImpl pokemonService;

    @Autowired
    private ModelMapper modelmapper;

    private static final Logger logger = LogManager.getLogger(PokemonController.class);

//    POST http://localhost:8080/api/pokemon/addMultiplePokemon
    @PostMapping("/addMultiplePokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Pokemon> saveMultiplePokemon(@RequestBody List<Pokemon> pokemon){
        logger.debug("Received request to create the {}", pokemon);
        return pokemonService.savePokemon(pokemon);
    }

    //GET http://localhost:8080/api/pokemon/findAllPokemon
    @GetMapping("/findAllPokemon")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonResponse> getAllPokemon(){
        logger.info("Received request to list all users");
        List<Pokemon> pokemon = new ArrayList<>();
        pokemon = (List<Pokemon>) pokemonService.getAllPokemon();
        List<PokemonResponse> pokemonResponse = new ArrayList<PokemonResponse>();

        for (Pokemon pokemon1 :pokemon){
          pokemonResponse.add(modelmapper.map(pokemon1,PokemonResponse.class));
       }
        return pokemonResponse;

    }

    // GET http://localhost:8080/api/pokemon/findOnePokemon/1024
    @GetMapping("/findOnePokemon/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Pokemon> getSinglePokemon(@PathVariable("id") int id) {
        return pokemonService.findOnePokemon(id);
    }

    //
    @PutMapping("/updatePokemon/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon updatePokemon(
            @PathVariable int id, @RequestBody Pokemon pokemonRequest){
        pokemonRequest.setPokemonId(id);
        return pokemonService.updatePokemon(pokemonRequest);

    }

    @RequestMapping(value = "/{pokemonId}",method = RequestMethod.DELETE)
    public String deletePokemon(@PathVariable int pokemonId){
        pokemonService.deletePokemon(pokemonId);
        return pokemonId + " is deleted";
    }

}
