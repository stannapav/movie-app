package org.example.movieapp.db.services;

import org.example.movieapp.db.repositories.CastRepository;
import org.springframework.stereotype.Service;

@Service
public class CastService {
    final CastRepository castRepository;

    public CastService(CastRepository castRepository) {
        this.castRepository = castRepository;
    }
}
