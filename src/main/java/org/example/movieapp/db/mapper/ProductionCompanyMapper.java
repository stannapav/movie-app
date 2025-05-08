package org.example.movieapp.db.mapper;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.ProductionCompanyDTO;
import org.example.movieapp.db.entities.Movie;
import org.example.movieapp.db.entities.ProductionCompany;
import org.example.movieapp.db.repositories.MovieRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductionCompanyMapper {

    private final MovieRepository movieRepository;

    public ProductionCompanyDTO toDTO(ProductionCompany company) {
        ProductionCompanyDTO dto = new ProductionCompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setMovieIds(company.getMovies().stream().map(Movie::getId).toList());
        return dto;
    }

    public ProductionCompany toEntity(ProductionCompanyDTO dto) {
        ProductionCompany company = new ProductionCompany();
        company.setId(dto.getId());
        company.setName(dto.getName());
        company.setMovies(movieRepository.findAllById(dto.getMovieIds()));
        return company;
    }
}
