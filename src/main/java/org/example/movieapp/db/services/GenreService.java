package org.example.movieapp.db.services;

import org.example.movieapp.db.repositories.GenreRepository;
import org.example.movieapp.db.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    final GenreRepository genreRepository;
    final MovieRepository movieRepository;

    public GenreService(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }
}
