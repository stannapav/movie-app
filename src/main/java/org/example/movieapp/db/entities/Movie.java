package org.example.movieapp.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    private Integer id;

    @Column(name = "imdb_id")
    private String imdbId;

    private String title; //in english

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(name = "release_date")
    @JsonFormat(pattern = "yyyy-MM-dd")  // Format: 2024-11-28
    private LocalDate releaseDate;

    @Column(name = "original_language")
    private String originalLanguage;

    @Column(name = "original_title")
    private String originalTitle;
    
    private float runtime; //in minutes

    @Column(name = "vote_average")
    private float voteAverage;

    @Column(name = "vote_count")
    private int voteCount;

    private int adult;
    
    //Many-to-Many
    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<Genre> genres = new ArrayList<>();
    
    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private List<ProductionCountry> productionCountries = new ArrayList<>();

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    private  List<ProductionCompany> productionCompanies = new ArrayList<>();
}
