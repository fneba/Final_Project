package com.company.gamestore.repositories;

import com.company.gamestore.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    private Game game;
    private Game game2;

    @BeforeEach
    public void setUp() throws Exception {
        gameRepository.deleteAll();
        // ARRANGE
        game = new Game();
        game.setTitle("Horizom");
        game.setDescription("Fun");
        game.setEsrbRating("R");
        game.setQuantity(5);
        game.setStudio("Insomnia");
        game.setPrice(new BigDecimal("59.99"));


        game2 = new Game();
        game2.setTitle("Horizom");
        game2.setDescription("Fun");
        game2.setEsrbRating("R");
        game2.setQuantity(5);
        game2.setStudio("Insomnia");
        game2.setPrice(new BigDecimal("59.99"));

    }

    @Test
    public void shouldGetAllGames(){


        game = gameRepository.save(game);



        game2 = gameRepository.save(game2);

        // ASSERT
        List<Game> gameList = gameRepository.findAll();

        assertEquals(2, gameList.size());

    }

    @Test
    public void shouldGetGameById(){

        game = gameRepository.save(game);

        // ACT
        Game retrievedGame = gameRepository.findById(game.getGameId()).orElse(null);

        // ASSERT

        assertEquals(new BigDecimal("59.99"), retrievedGame.getPrice());
    }

    @Test
    public void shouldGetGameByEsrb(){

        // ARRANGE

        game = gameRepository.save(game);

        // ACT
        List<Game> retrievedGame = gameRepository.findByEsrbRating("R");

        // ASSERT 
        assertEquals(1, retrievedGame.size());



    }


    @Test
    public void shouldAddGame(){

        // ARRANGE
        game = gameRepository.save(game);

        // ACT

        Optional<Game> game1 = gameRepository.findById(game.getGameId());
        assertEquals(game.getGameId(), game1.get().getGameId());
    }

    @Test
    public void shouldUpdateGame(){

        game.setPrice(new BigDecimal("79.99"));
        gameRepository.save(game);

        Optional game1 = gameRepository.findById(game.getGameId());
        assertEquals(game1.get(), game);

    }

    @Test
    public void shouldDeleteGame(){

        // ARRANGE

        game = gameRepository.save(game);

        // ACT
        gameRepository.deleteById(game.getGameId());

        // ASSERT
        assertFalse(gameRepository.existsById(game.getGameId()));
    }

}
