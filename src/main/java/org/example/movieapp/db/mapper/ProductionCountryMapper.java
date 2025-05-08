package org.example.movieapp.db.mapper;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.ProductionCountryDTO;
import org.example.movieapp.db.entities.Movie;
import org.example.movieapp.db.entities.ProductionCountry;
import org.example.movieapp.db.repositories.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductionCountryMapper {

    private final MovieRepository movieRepository;

    public ProductionCountryDTO toDTO(ProductionCountry country) {
        ProductionCountryDTO dto = new ProductionCountryDTO();
        dto.setCode(country.getCode());
        dto.setName(country.getName());
        return dto;
    }

    public ProductionCountry toEntity(ProductionCountryDTO dto) {
        ProductionCountry country = new ProductionCountry();
        country.setCode(dto.getCode());
        country.setName(dto.getName());
        return country;
    }
}

