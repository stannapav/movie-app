package org.example.movieapp.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.enums.WatchStatus;
import org.example.movieapp.db.services.UserMovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user-movies")
@RequiredArgsConstructor
public class UserMovieController {
    private final UserMovieService userMovieService;
    
    @PostMapping("/{userId}/{movieId}")
    public ResponseEntity<String> setWatchStatus(
            @PathVariable Integer userId,
            @PathVariable Integer movieId,
            @RequestParam WatchStatus status
    ) {
        try {
            userMovieService.saveWatchStatus(userId, movieId, status);
            return ResponseEntity.ok("Status saved");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieDTO>> getMoviesByStatus(
            @PathVariable Integer userId,
            @RequestParam WatchStatus status
    ) {
        List<MovieDTO> movies = userMovieService.getUserMoviesByStatus(userId, status);
        return ResponseEntity.ok(movies);
    }
}
