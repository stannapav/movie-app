package org.example.movieapp.db.services;

import org.example.movieapp.db.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    final MovieRepository movieRepository;
    final CastRepository castRepository;
    final CrewRepository crewRepository;
    final GenreRepository genreRepository;
    final ProductionCompanyRepository companyRepository;
    final ProductionCountryRepository countryRepository;

    public MovieService(MovieRepository movieRepository, CastRepository castRepository, CrewRepository crewRepository, GenreRepository genreRepository, ProductionCompanyRepository companyRepository, ProductionCountryRepository countryRepository) {
        this.movieRepository = movieRepository;
        this.castRepository = castRepository;
        this.crewRepository = crewRepository;
        this.genreRepository = genreRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }
}
