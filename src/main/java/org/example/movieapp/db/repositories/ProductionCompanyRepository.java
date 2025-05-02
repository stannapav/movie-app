package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.ProductionCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Integer> {
}
