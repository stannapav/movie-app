package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.movieapp.db.dto.ProductionCountryDTO;
import org.example.movieapp.db.entities.ProductionCountry;
import org.example.movieapp.mappers.ProductionCountryMapper;
import org.example.movieapp.db.repositories.ProductionCountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCountryService {
    final ProductionCountryRepository countryRepository;
    final ProductionCountryMapper countryMapper;
    
    public ProductionCountryService(ProductionCountryRepository countryRepository, ProductionCountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public ProductionCountryDTO getCountryDTOById(String code) {
        ProductionCountry country = countryRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
        return countryMapper.toDTO(country);
    }
    
    public List<ProductionCountryDTO> getAllCountries(){
        return countryRepository.findAll().stream().map(countryMapper::toDTO).toList();
    }
}
