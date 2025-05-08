package org.example.movieapp.db.mapper;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.GenreDTO;
import org.example.movieapp.db.entities.Genre;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreMapper {
    public GenreDTO toDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }

    public Genre toEntity(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        return genre;
    }
}

