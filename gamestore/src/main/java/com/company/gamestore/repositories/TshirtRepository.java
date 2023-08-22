package com.company.gamestore.repositories;

import com.company.gamestore.models.Tshirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TshirtRepository extends JpaRepository<Tshirt, Integer> {
}
