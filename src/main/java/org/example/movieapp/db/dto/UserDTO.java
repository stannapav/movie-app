package org.example.movieapp.db.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String email;
    private String name;

    private List<MovieDTO> watchedMovies;
    private List<MovieDTO> watchLaterMovies;
    private List<MovieDTO> droppedMovies;
}
