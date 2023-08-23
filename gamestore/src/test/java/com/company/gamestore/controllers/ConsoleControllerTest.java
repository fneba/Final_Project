package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ConsoleRepository consoleRepository;
    private ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    public void setup() {

    }

    //CREATE
    @Test
    public void shouldCreateAuthor() throws Exception {

        //Arrange...
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(499);
        console.setProcessor("Intel I9");
        console.setMemory_amount("1000000");

        String inputJson = mapper.writeValueAsString(console);

        mockMvc.perform(post("/console")
                        .content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

}
