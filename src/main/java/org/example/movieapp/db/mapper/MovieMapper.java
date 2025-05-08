package org.example.movieapp.db.mapper;

import org.example.movieapp.db.dto.MovieDTO;
import org.example.movieapp.db.entities.*;
import org.example.movieapp.db.repositories.*;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final GenreRepository genreRepository;
    private final ProductionCompanyRepository productionCompanyRepository;
    private final ProductionCountryRepository productionCountryRepository;
    private final CastRepository castRepository;
    private final CrewRepository crewRepository;

    public MovieDTO toDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setImdbId(movie.getImdbId());
        dto.setTitle(movie.getTitle());
        dto.setOverview(movie.getOverview());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setOriginalLanguage(movie.getOriginalLanguage());
        dto.setOriginalTitle(movie.getOriginalTitle());
        dto.setRuntime(movie.getRuntime());
        dto.setVoteAverage(movie.getVoteAverage());
        dto.setVoteCount(movie.getVoteCount());
        dto.setAdult(movie.getAdult());

        dto.setGenreIds(movie.getGenres().stream().map(Genre::getId).collect(Collectors.toList()));
        dto.setProductionCompanyIds(movie.getProductionCompanies().stream().map(ProductionCompany::getId).collect(Collectors.toList()));
        dto.setProductionCountryCodes(movie.getProductionCountries().stream().map(ProductionCountry::getCode).collect(Collectors.toList()));
        dto.setCastIds(movie.getCastList().stream().map(Cast::getId).collect(Collectors.toList()));
        dto.setCrewIds(movie.getCrewList().stream().map(Crew::getId).collect(Collectors.toList()));

        return dto;
    }

    public Movie toEntity(MovieDTO dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setImdbId(dto.getImdbId());
        movie.setTitle(dto.getTitle());
        movie.setOverview(dto.getOverview());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setOriginalLanguage(dto.getOriginalLanguage());
        movie.setOriginalTitle(dto.getOriginalTitle());
        movie.setRuntime(dto.getRuntime());
        movie.setVoteAverage(dto.getVoteAverage());
        movie.setVoteCount(dto.getVoteCount());
        movie.setAdult(dto.getAdult());

        movie.setGenres(genreRepository.findAllById(dto.getGenreIds()));
        movie.setProductionCompanies(productionCompanyRepository.findAllById(dto.getProductionCompanyIds()));
        movie.setProductionCountries(productionCountryRepository.findAllByCode(String.valueOf(dto.getProductionCountryCodes())));
        movie.setCastList(castRepository.findAllById(dto.getCastIds()));
        movie.setCrewList(crewRepository.findAllById(dto.getCrewIds()));

        return movie;
    }
}