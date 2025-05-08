package org.example.movieapp.controllers;

import org.example.movieapp.db.dto.GenreDTO;
import org.example.movieapp.db.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    final GenreService genreService;

    public GenreController(GenreService genreService) { this.genreService = genreService; }
    
    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable Integer genreId){
        return ResponseEntity.ok(genreService.getGenreById(genreId));
    }
    
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres(){
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
