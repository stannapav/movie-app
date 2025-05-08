package org.example.movieapp.controllers;

import org.example.movieapp.db.dto.ProductionCompanyDTO;
import org.example.movieapp.db.services.ProductionCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class ProductionCompanyController {
    final ProductionCompanyService companyService;

    public ProductionCompanyController(ProductionCompanyService companyService) {
        this.companyService = companyService;
    }
    
    @GetMapping("/{companyId}")
    public ResponseEntity<ProductionCompanyDTO> getCompany(@PathVariable Integer companyId){
        return ResponseEntity.ok(companyService.getCountryById(companyId));
    }
    
    @GetMapping
    public ResponseEntity<Page<ProductionCompanyDTO>> getAllCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return ResponseEntity.ok(companyService.getAllCompanies(page, size));
    }
}
