package com.company.gamestore.repositories;


import com.company.gamestore.models.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TaxRepository extends JpaRepository<Tax, String> {

    Double findRateByState(String state);


}
