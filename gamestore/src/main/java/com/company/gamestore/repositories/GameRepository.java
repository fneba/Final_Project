package com.company.gamestore.repositories;


import com.company.gamestore.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    //By Studio
    List<Game> findByStudio(String studio);


    //By ESRB
    List<Game> findByEsrbRating(String esrb);

    //By Title
    List<Game> findByTitle(String title);
}
