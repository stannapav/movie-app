package org.example.movieapp.db.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crew")
public class Crew {
    @Id
    private Integer id;
    
    @Column(name = "crew_name")
    private String crewName;
    
    private String department;
    
    private String job;

    //Many-to-One
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
