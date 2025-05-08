package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByNameIn(List<String> name);
}
