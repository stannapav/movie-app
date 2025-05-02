package org.example.movieapp.db.services;

import org.example.movieapp.db.repositories.MovieRepository;
import org.example.movieapp.db.repositories.ProductionCompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductionCompanyService {
    final ProductionCompanyRepository companyRepository;
    final MovieRepository movieRepository;

    public ProductionCompanyService(ProductionCompanyRepository companyRepository, MovieRepository movieRepository) {
        this.companyRepository = companyRepository;
        this.movieRepository = movieRepository;
    }
}
