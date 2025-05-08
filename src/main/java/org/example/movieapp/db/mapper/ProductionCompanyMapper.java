package org.example.movieapp.db.mapper;

import lombok.RequiredArgsConstructor;
import org.example.movieapp.db.dto.ProductionCompanyDTO;
import org.example.movieapp.db.entities.ProductionCompany;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductionCompanyMapper {
    public ProductionCompanyDTO toDTO(ProductionCompany company) {
        ProductionCompanyDTO dto = new ProductionCompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        return dto;
    }

    public ProductionCompany toEntity(ProductionCompanyDTO dto) {
        ProductionCompany company = new ProductionCompany();
        company.setId(dto.getId());
        company.setName(dto.getName());
        return company;
    }
}
