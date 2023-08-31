package com.company.gamestore.repositories;

import com.company.gamestore.models.Tshirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TshirtRepositoryTest {

    @Autowired
    TshirtRepository tshirtRepository;


    @BeforeEach
    public void setUp() throws Exception {
        tshirtRepository.deleteAll();
    }

    @Test
    public void shouldGetAllTshirts(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("White");
        tshirt2.setDescription("Medium white tshirt.");
        tshirt2.setSize("Medium");
        tshirt2.setQuantity(5);
        tshirt2.setPrice(3.99);

        tshirt2 = tshirtRepository.save(tshirt2);

        // ASSERT
        List<Tshirt> tshirtList = tshirtRepository.findAll();

        assertEquals(2, tshirtList.size());

    }

    @Test
    public void shouldGetTshirtById(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        // ACT
        Tshirt retrievedTshirt = tshirtRepository.findById(tshirt.getId()).orElse(null);

        // ASSERT
        assertNotNull(retrievedTshirt);
        assertEquals("Red", retrievedTshirt.getColor());
        assertEquals("Big red tshirt.", retrievedTshirt.getDescription());
        assertEquals("Large", retrievedTshirt.getSize());
        assertEquals(5, retrievedTshirt.getQuantity());
        assertEquals(5.99, retrievedTshirt.getPrice());
    }

    @Test
    public void shouldGetTshirtByColor(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("White");
        tshirt2.setDescription("Medium white tshirt.");
        tshirt2.setSize("Medium");
        tshirt2.setQuantity(5);
        tshirt2.setPrice(3.99);

        tshirt2 = tshirtRepository.save(tshirt2);

        // ACT
        List<Tshirt> retrievedTshirts = tshirtRepository.findByColor("Red");

        // ASSERT
        assertEquals(1, retrievedTshirts.size());



    }

    @Test
    public void shouldGetTshirtBySize(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("White");
        tshirt2.setDescription("Medium white tshirt.");
        tshirt2.setSize("Medium");
        tshirt2.setQuantity(5);
        tshirt2.setPrice(3.99);

        tshirt2 = tshirtRepository.save(tshirt2);

        // ACT
        List<Tshirt> retrievedTshirts = tshirtRepository.findBySize("Medium");

        // ASSERT
        assertEquals(1, retrievedTshirts.size());
    }

    @Test
    public void shouldAddTshirt(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        // ACT

        Tshirt savedTshirt = tshirtRepository.save(tshirt);

        // ASSERT
        assertNotNull(savedTshirt);
        assertEquals(tshirt.getColor(), savedTshirt.getColor());
    }

    @Test
    public void shouldUpdateTshirt(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        // ACT
        tshirt.setColor("White");
        Tshirt updatedTshirt = tshirtRepository.save(tshirt);

        // ASSERT
        assertEquals(tshirt.getId(), updatedTshirt.getId());
        assertEquals("White", updatedTshirt.getColor());
    }

    @Test
    public void shouldDeleteTshirt(){

        // ARRANGE
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Red");
        tshirt.setDescription("Big red tshirt.");
        tshirt.setSize("Large");
        tshirt.setQuantity(5);
        tshirt.setPrice(5.99);

        tshirt = tshirtRepository.save(tshirt);

        // ACT
        tshirtRepository.deleteById(tshirt.getId());

        // ASSERT
        assertFalse(tshirtRepository.existsById(tshirt.getId()));
    }

}