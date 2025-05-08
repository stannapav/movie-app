package org.example.movieapp.db.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductionCompanyDTO {
    private Integer id;
    private String name;
    private List<Integer> movieIds;
}