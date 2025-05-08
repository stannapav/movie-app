package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findALLByGenres_IdOrderByVoteCountDesc(Integer id, Pageable pageable);

}
