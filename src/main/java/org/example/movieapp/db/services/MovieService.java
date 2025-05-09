package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.entities.Movie;
import org.example.movieapp.mappers.MovieMapper;
import org.example.movieapp.db.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieDTO getMovieById(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        return movieMapper.toDTO(movie);
    }

    public Page<MovieDTO> getAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable)
                .map(movieMapper::toDTO);
    }

    public Page<MovieDTO> getMoviesByGenre(Integer genreId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findALLByGenres_IdOrderByVoteCountDesc(genreId, pageable)
                .map(movieMapper::toDTO);
    }
}
