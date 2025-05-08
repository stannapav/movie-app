package org.example.movieapp.db.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Integer id;
    private String imdbId;
    private String title;
    private String overview;
    private LocalDate releaseDate;
    private String originalLanguage;
    private String originalTitle;
    private float runtime;
    private float voteAverage;
    private int voteCount;
    private int adult;
    
    private List<Integer> genreIds;
    private List<String> productionCountryCodes;
    private List<Integer> productionCompanyIds;
}