package org.example.movieapp.db.services;

import org.example.movieapp.db.repositories.MovieRepository;
import org.example.movieapp.db.repositories.ProductionCountryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductionCountryService {
    final ProductionCountryRepository countryRepository;
    final MovieRepository movieRepository;
    
    public ProductionCountryService(ProductionCountryRepository countryRepository, MovieRepository movieRepository) {
        this.countryRepository = countryRepository;
        this.movieRepository = movieRepository;
    }
}
