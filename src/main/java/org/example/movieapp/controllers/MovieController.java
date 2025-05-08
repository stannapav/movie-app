package org.example.movieapp.controllers;

import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Integer movieId){
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(movieService.getAllMovies(page, size));
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<Page<MovieDTO>> getAllMovies(
            @PathVariable Integer genreId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genreId, page, size));
    }
}
