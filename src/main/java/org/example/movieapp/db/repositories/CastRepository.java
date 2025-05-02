package org.example.movieapp.db.repositories;

import org.example.movieapp.db.entities.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<Cast, Integer> {
}
