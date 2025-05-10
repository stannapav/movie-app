package org.example.movieapp.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.GenreDTO;
import org.example.movieapp.db.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    final GenreService genreService;
    
    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable Integer genreId){
        try {
            return ResponseEntity.ok(genreService.getGenreById(genreId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres(){
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
