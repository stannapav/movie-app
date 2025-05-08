package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.entities.Movie;
import org.example.movieapp.db.mapper.MovieMapper;
import org.example.movieapp.db.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    final MovieRepository movieRepository;
    final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public MovieDTO getMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        return movieMapper.toDTO(movie);
    }

//    public List<Movie> getMovies(int page, int pageSize){
//        Sort sort = Sort.by("id").ascending();
//        PageRequest pageable = PageRequest.of(page, pageSize, sort);
//
//        return movieRepository.findAll(pageable).stream().toList();
//    }
}
