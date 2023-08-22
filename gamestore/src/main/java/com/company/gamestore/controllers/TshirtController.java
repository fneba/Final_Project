package com.company.gamestore.controllers;

import com.company.gamestore.models.Tshirt;
import com.company.gamestore.repositories.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {

    @Autowired
    TshirtRepository repo;

    // Read (All)
    @GetMapping("/tshirts")
    public List<Tshirt> getAlbum(){
        return repo.findAll();
    }

    // Create
    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt (@RequestBody Tshirt tshirt) {
        return repo.save(tshirt);
    }

    // Custom Method 1
    @GetMapping("/tshirts/color")
    public List<Tshirt> getTshirtByColor(@PathVariable String color) {
        return repo.findByColor(color);
    }

    // Custome Method 2
    @GetMapping("/tshirts/size")
    public List<Tshirt> getTshirtBySize(@PathVariable String size) {
        return repo.findBySize(size);
    }

    // Read (by id)
    @GetMapping("/tshirts/{id}")
    public Tshirt getTshirtById(@PathVariable int id){
        Optional<Tshirt> returnVal = repo.findById(id);
        if (returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    // Update
    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody Tshirt tshirt){

        repo.save(tshirt);
    }

    // Delete
    @DeleteMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int id){
        repo.deleteById(id);
    }
}
