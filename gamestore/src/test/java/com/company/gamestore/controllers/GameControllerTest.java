package com.company.gamestore.controllers;

import com.company.gamestore.models.Game;
import com.company.gamestore.repositories.GameRepository;
import com.company.gamestore.repositories.ProcessingFeeRepository;
import com.company.gamestore.repositories.TaxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    // Wiring in the MockMvc object
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GameRepository gameRepository;

    @MockBean
    TaxRepository taxRepository;

    @MockBean
    ProcessingFeeRepository processingFeeRepository;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();

    // A list of game for testing purposes
    private List<Game> gameList;

    private Game game;

    @BeforeEach
    public void setUp() {
        // Standard set up method, for instantiating test objects
        // Don't have to do anything special for mockMvc since it's Autowired
        game = new Game();
        game.setTitle("Horizom");
        game.setDescription("Fun");
        game.setEsrbRating("R");
        game.setQuantity(5);
        game.setStudio("Insomnia");
        game.setPrice(new BigDecimal("59.99"));

    }


    // Testing GET /game
    @Test
    public void shouldReturnAllGames() throws Exception {

        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(gameList);

        // ACT
        mockMvc.perform(get("/game"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }

    // Testing POST /game
    @Test
    public void shouldReturnNewGameOnPostRequest() throws Exception {
        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(game);

        Game game2 = new Game();
        game2.setTitle("Horizom");
        game2.setDescription("Fun");
        game2.setEsrbRating("R");
        game2.setQuantity(5);
        game2.setStudio("Insomnia");
        game2.setPrice(new BigDecimal("59.99"));
        game2.setGameId(1);


        String outputJson = mapper.writeValueAsString(game2);

        // ACT
        mockMvc.perform(
                        post("/game")                            // Perform the POST request
                                .content(inputJson)                       // Set the request body
                                .contentType(MediaType.APPLICATION_JSON)  // Tell the server it's in JSON format
                )
                .andDo(print())                                // Print results to console
                .andExpect(status().isCreated());              // ASSERT (status code is 201)
    }


    // Testing GET game/{id}
    @Test
    public void shouldReturnGameById() throws Exception {

        mockMvc.perform(get("/game/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    // Testing PUT /game/{id}
    @Test
    public void shouldUpdateGame() throws Exception {

        // This method returns nothing, so we're just checking for correct status code

        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(
                        put("/game")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing DELETE /game/{id}
    @Test
    public void shouldDeleteByIdAndReturn204StatusCode() throws Exception {

        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        mockMvc.perform(delete("/game/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    // Testing GET Studio /game/studio/{studio}")
    @Test
    public void shouldgetGameByStudio() throws Exception{
        String outputJson = mapper.writeValueAsString(game);

        mockMvc.perform(get("/game/studio/Insomnia"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    // Testing GET /game/esrb/{esrb}")
    @Test
    public void shouldGetGameByEsrb() throws Exception{
        String outputJson = mapper.writeValueAsString(game);

        mockMvc.perform(get("/game/esrb/R"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    //By /game/title/{title}
    @Test
    public void shouldGetGameByTitle() throws Exception{
        String outputJson = mapper.writeValueAsString(game);

        mockMvc.perform(get("/game/title/Horizon"))
                .andDo(print())
                .andExpect(status().isOk());


    }

}
