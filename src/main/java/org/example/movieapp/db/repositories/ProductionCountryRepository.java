package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Integer> {
    Optional<ProductionCountry> findByCode(String code);

    List<ProductionCountry> findByNameIn(List<String> name);
}
