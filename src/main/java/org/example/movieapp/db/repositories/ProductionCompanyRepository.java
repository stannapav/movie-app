package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Integer> {
    List<ProductionCompany> findByNameIn(List<String> name);
}
