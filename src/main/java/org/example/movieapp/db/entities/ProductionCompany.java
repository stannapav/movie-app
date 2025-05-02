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
@Table(name = "production_companies")
public class ProductionCompany {
    @Id
    private Integer id;
    
    private String name;

    //Many-to-Many
    @ManyToMany
    @JoinTable(
        name = "movie_production_companies",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies = new ArrayList<>();
}
