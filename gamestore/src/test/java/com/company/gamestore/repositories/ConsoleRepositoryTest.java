
package com.company.gamestore.repositories;

import com.company.gamestore.models.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository repository;

    @BeforeEach
    public void setUp() throws Exception {
        repository.deleteAll();
    }

    //CREATE
    @Test
    public void createConsole(){
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);
        Optional<Console> console1 = repository.findById(console.getId());
        assertEquals(console1.get(), console);

    }

    //UPDATE
    @Test
    public void shouldUpdateController() {
        //Arrange...
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);
        console.setModel("PS4");
        repository.save(console);
        //Act...
        Optional<Console> console1 = repository.findById(console.getId());
        //Assert...
        assertEquals(console1.get(), console);
    }

    //DELETE
    @Test
    public void shouldDeleteConsoleById() {

        //Arrange...
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);
        Optional<Console> console1 = repository.findById(console.getId());
        assertEquals(console1.get(), console);
        //Act...
        repository.deleteById(console.getId());
        console1 = repository.findById(console.getId());
        //Assert...
        assertFalse(console1.isPresent());
    }

    //Find console record by id.
    @Test
    public void shouldGetConsoleById() {

        //Arrange...
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);

        Optional<Console> foundConsole = repository.findById(console.getId());

        //Assert...
        assertEquals(foundConsole.get(), console);
    }

    //READ ALL
    @Test
    public void shouldGetAll() {

        //Arrange...
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);

        //Arrange...
        console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);
        //Act...
        List<Console> lList = repository.findAll();
        //Assert...
        assertEquals(lList.size(), 2);
    }

    //GET Console By Manufacturer
    @Test
    public void shouldGetConsoleByManufacturer() {

        //Arrange...
        Console console = new Console();
        console.setModel("PS5");
        console.setManufacturer("Sony");
        console.setQuantity(1);
        console.setPrice(new BigDecimal("499"));
        console.setProcessor("Intel I9");
        console.setMemoryAmount("1000000");

        //Act...
        console = repository.save(console);

        //Assert...
        List<Console> aList = repository.findByManufacturer(console.getManufacturer());
        assertEquals(aList.size(), 1);
    }

}