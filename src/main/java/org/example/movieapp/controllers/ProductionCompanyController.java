package org.example.movieapp.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.ProductionCompanyDTO;
import org.example.movieapp.db.services.ProductionCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class ProductionCompanyController {
    final ProductionCompanyService companyService;
    
    @GetMapping("/{companyId}")
    public ResponseEntity<ProductionCompanyDTO> getCompany(@PathVariable Integer companyId){
        try {
            return ResponseEntity.ok(companyService.getCountryById(companyId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<ProductionCompanyDTO>> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(companyService.getAllCompanies(page, size));
    }
}
