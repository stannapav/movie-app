package org.example.movieapp.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/movies")
public class MovieController {
    final MovieService movieService;
    
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Integer movieId){
        try {
            return ResponseEntity.ok(movieService.getMovieById(movieId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/title")
    public ResponseEntity<MovieDTO> getMovieByTitle(@RequestParam String title){
        try {
            return ResponseEntity.ok(movieService.getMovieByTitle(title));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(movieService.getAllMovies(page, size));
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<Page<MovieDTO>> getAllMoviesByGenre(
            @PathVariable Integer genreId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(movieService.getMoviesByGenre(genreId, page, size));
    }
}
