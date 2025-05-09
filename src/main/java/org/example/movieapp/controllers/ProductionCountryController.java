package org.example.movieapp.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.ProductionCountryDTO;
import org.example.movieapp.db.services.ProductionCountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/countries")
public class ProductionCountryController {
    final ProductionCountryService countryService;

    @GetMapping("/{countryCode}")
    public ResponseEntity<ProductionCountryDTO> getCountry(@PathVariable String countryCode){
        try {
            return ResponseEntity.ok(countryService.getCountryDTOById(countryCode));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ProductionCountryDTO>> getCountries(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }
}
