package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.UserMovie;
import org.example.movieapp.db.enums.WatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, Integer> {
    Optional<UserMovie> findByUser_IdAndMovie_Id(Integer userId, Integer movieId);

    List<UserMovie> findByUser_IdAndWatchStatus(Integer id, WatchStatus watchStatus);
}
