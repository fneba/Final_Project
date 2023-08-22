package com.company.gamestore.controllers;

import com.company.gamestore.models.Console;
import com.company.gamestore.repositories.ConsoleRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository repo;

    //CREATE CONSOLE
    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return repo.save(console);
    }

    //READ ALL
    @GetMapping("/console")
    public List<Console> getConsole() {
        return repo.findAll();
    }

    //READ A CONSOLE (BY ID)
    @GetMapping("/console/{id}")
    public Console getConsoleById(@PathVariable int id) {
        Optional<Console> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    //UPDATE CONSOLE
    @PutMapping("/console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console) {
        repo.save(console);
    }

    //A DELETE route that deletes an existing console.
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        repo.deleteById(id);
    }

    //FIND CONSOLE BY MANUFACTURER
    @GetMapping("/console/manufacturer/{id}")
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        List<Console> returnVal = repo.findByManufacturer(manufacturer);
        if (returnVal.isEmpty() == false) {
            return returnVal;
        } else {
            return null;
        }
    }
}
