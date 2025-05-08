package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.movieapp.db.dto.GenreDTO;
import org.example.movieapp.db.entities.Genre;
import org.example.movieapp.db.mapper.GenreMapper;
import org.example.movieapp.db.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    final GenreRepository genreRepository;
    final GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }
    
    public GenreDTO getGenreById(Integer id){
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found"));
        return genreMapper.toDTO(genre);
    }
    
    public List<GenreDTO> getAllGenres(){
        return genreRepository.findAll().stream().map(genreMapper::toDTO).toList();
    }
}
