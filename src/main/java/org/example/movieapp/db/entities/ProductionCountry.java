package org.example.movieapp.db.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "production_countries")
public class ProductionCountry {
    @Id
    @Column(name = "iso_3166_1", columnDefinition = "CHAR(2)")
    private String code; //en, jp ...
    
    private String name;

    //Many-to-Many
    @ManyToMany
    @JoinTable(
        name = "movie_production_countries",
        joinColumns = @JoinColumn(name = "country_iso", columnDefinition = "CHAR(2)"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies = new ArrayList<>();
}
