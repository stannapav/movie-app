package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Integer> {
}
