package org.example.movieapp.db.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.movieapp.db.dto.ProductionCompanyDTO;
import org.example.movieapp.db.entities.ProductionCompany;
import org.example.movieapp.mappers.ProductionCompanyMapper;
import org.example.movieapp.db.repositories.ProductionCompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductionCompanyService {
    final ProductionCompanyRepository companyRepository;
    final ProductionCompanyMapper companyMapper;

    public ProductionCompanyService(ProductionCompanyRepository companyRepository, ProductionCompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public ProductionCompanyDTO getCountryById(Integer id) {
        ProductionCompany company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        return companyMapper.toDTO(company);
    }

    public Page<ProductionCompanyDTO> getAllCompanies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findAll(pageable)
                .map(companyMapper::toDTO);
    }
}
