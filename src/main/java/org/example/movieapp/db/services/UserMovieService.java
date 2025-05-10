package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.entities.Movie;
import org.example.movieapp.db.entities.UserModel;
import org.example.movieapp.db.entities.UserMovie;
import org.example.movieapp.db.enums.WatchStatus;
import org.example.movieapp.db.repositories.MovieRepository;
import org.example.movieapp.db.repositories.UserMovieRepository;
import org.example.movieapp.db.repositories.UserRepository;
import org.example.movieapp.mappers.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserMovieService {
    private final UserMovieRepository userMovieRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieMapper movieMapper;

    @Transactional
    public void saveWatchStatus(Integer userId, Integer movieId, WatchStatus status) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        
        Optional<UserMovie> existing = userMovieRepository.findByUser_IdAndMovie_Id(userId, movieId);

        UserMovie userMovie;
        if (existing.isPresent()) {
            userMovie = existing.get();
        } else {
            userMovie = new UserMovie();
            userMovie.setUser(user);
            userMovie.setMovie(movie);
        }
        
        userMovie.setWatchStatus(status);
        userMovieRepository.save(userMovie);
    }

    public List<MovieDTO> getUserMoviesByStatus(Integer userId, WatchStatus status) {
        return userMovieRepository.findByUser_IdAndWatchStatus(userId, status)
                .stream()
                .map(userMovie -> movieMapper.toDTO(userMovie.getMovie()))
                .toList();
    }
}
