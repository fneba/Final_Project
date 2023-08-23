package com.company.gamestore.controllers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    GameRepository repo;


    //Create,
    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame (@RequestBody Game game) {
        return repo.save(game);
    }

    //Read All,
    @GetMapping("/game")
    public List<Game> getGame(){
        return repo.findAll();
    }

    //Read,
    @GetMapping("/game/{id}")
    public Game getGameById(@PathVariable int id){
        Optional<Game> returnVal = repo.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }



    //Update,
    @PutMapping("/game")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game){
        repo.save(game);
    }

    //Delete,
    @DeleteMapping("/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id){
        repo.deleteById(id);}


    //By Studio,
    @GetMapping("/game/studio/{studio}")
    public List<Game> getGameByStudio(@PathVariable String studio){
        return repo.findByStudio(studio);

    }

    //By ESRB,
    @GetMapping("/game/esrb/{esrb}")
    public List<Game> getGameByEsrb(@PathVariable String esrb){
        return repo.findByEsrbRating(esrb);

    }

    //By Title.
    @GetMapping("/game/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title){
        return repo.findByTitle(title);

    }
}
