package com.company.gamestore.controllers;

import com.company.gamestore.models.Tshirt;
import com.company.gamestore.repositories.TshirtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(TshirtController.class)
class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TshirtRepository tshirtRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGetTshirts() throws Exception {

        // ACT
        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtById() throws Exception {

        // ACT
        mockMvc.perform(get("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtsByColor() throws Exception {

        // ACT
        mockMvc.perform(get("/tshirts/color/red"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtsBySize() throws Exception {

        // ACT
        mockMvc.perform(get("/tshirts/size/large"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddTshirt() throws Exception {

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(tshirt);

        // ACT

        mockMvc.perform(
                        post("/tshirts")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());


    }

    @Test
    public void shouldUpdateTshirt() throws Exception {

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("White");
        tshirt.setDescription("Big white tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(tshirt);

        // ACT
        mockMvc.perform(
                        put("/tshirts")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldDeleteTshirt() throws Exception {

        mockMvc.perform(delete("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}