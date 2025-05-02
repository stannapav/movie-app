package org.example.movieapp.db.services;

import org.example.movieapp.db.repositories.CrewRepository;
import org.springframework.stereotype.Service;

@Service
public class CrewService {
    final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }
}
