package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.ConsoleRepository;
import com.company.gamestore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ConsoleRepository consoleRepository;

    @QueryMapping
    public List<Console> consoles() { return consoleRepository.findAll(); }

    @QueryMapping
    public List<Game> games() {return gameRepository.findAll(); }

    @QueryMapping
    public Game findGameById(@Argument int id){
        Optional<Game> game = gameRepository.findById(id);

        return game.orElse(null);
    }

    @QueryMapping
    public List<Game> findGameByTitle(@Argument String title){
        List<Game> games = gameRepository.findByTitle(title);

        return games;
    }

    @QueryMapping
    public List<Game> findGameByEsrb(@Argument String esrbRating){
        List<Game> games = gameRepository.findByEsrbRating(esrbRating);

        return games;
    }

    @QueryMapping
    public List<Game> findGameByStudio(@Argument String studio){
        List<Game> games = gameRepository.findByStudio(studio);

        return games;

    }

    @QueryMapping
    public Console findConsoleById(@Argument int id){
        Optional<Console> console = consoleRepository.findById(id);

        return console.orElse(null);

    }

    @QueryMapping
    public List<Console> findConsoleByManufacturer(@Argument String manufacturer){
        List<Console> consoles = consoleRepository.findByManufacturer(manufacturer);

        return consoles;

    }
}
