package com.spring.jpa.pokemon.controller;

import com.spring.jpa.pokemon.dto.PokemonResponse;
import com.spring.jpa.pokemon.model.Pokemon;
import com.spring.jpa.pokemon.service.PokemonServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping("/addOnePokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon saveMultiplePokemon(@RequestBody Pokemon pokemon){
        logger.info("Received request to create the {}", pokemon);
        return pokemonService.saveOnePokemon(pokemon);
    }

//    POST http://localhost:8080/api/pokemon/addMultiplePokemon
    @PostMapping("/addMultiplePokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Pokemon> saveMultiplePokemon(@RequestBody List<Pokemon> pokemon){
        logger.info("Received request to create the {}", pokemon);
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

    @GetMapping("/findAllPokemonById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Pokemon> getMultiplePokemonById(@PathVariable("id") List<Integer> id) {
        return pokemonService.findAllPokemonById(id);
    }

    @GetMapping("/findPokemonByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Pokemon getMultiplePokemonById(@PathVariable("name") String name) {
        return pokemonService.findPokemonByName(name);
    }


    @PutMapping("/updatePokemon/{pokemonId}/updateType1/{type1Id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon updatePokemonForType1(
            @PathVariable int pokemonId,@PathVariable int type1Id,
            @RequestBody Pokemon pokemonRequest){

        return pokemonService.updatePokemonOnlyType1(pokemonId,type1Id,pokemonRequest);

    }
    // PUT
    @PutMapping("/updatePokemon/{pokemonId}/updateType/{type1Id}/updateType2/{type2Id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon updatePokemon(
            @PathVariable int pokemonId,@PathVariable int type1Id,
            @PathVariable int type2Id,
            @RequestBody Pokemon pokemonRequest){

        return pokemonService.updatePokemon(pokemonId,type1Id,type2Id,pokemonRequest);

    }

    @RequestMapping(value = "/{pokemonId}/type1/{type1Id}",method = RequestMethod.DELETE)
    public String deletePokemon(@PathVariable int pokemonId, @PathVariable int type1Id){
        pokemonService.deletePokemon(pokemonId,type1Id);
        return pokemonId + " is deleted";
    }

//    DELETE http://localhost:8080/api/pokemon/deleteAll
    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllPokemon(){
        pokemonService.deleteAllPokemon();
        return "All Pokemon are deleted";
    }

}
