package org.example.movieapp.db.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cast")
public class Cast {
    @Id
    private Integer id;
    
    @Column(name = "cast_id")
    private Integer castId;
    
    @Column(name = "character_name")
    private String characterName;

    @Column(name = "actor_name")
    private String actorName;
    
    @Column(name = "cast_order")
    private int castOrder;
    
    //Many-to-One
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
