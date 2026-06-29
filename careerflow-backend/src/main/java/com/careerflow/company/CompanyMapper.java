package com.careerflow.company;

import com.careerflow.company.dto.CompanyResponse;
import com.careerflow.company.dto.CreateCompanyRequest;
import com.careerflow.company.dto.UpdateCompanyRequest;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CreateCompanyRequest request) {
        Company company = new Company();

        company.setName(request.name());
        company.setDescription(request.description());
        company.setIndustry(request.industry());
        company.setWebsite(request.website());
        company.setLocation(request.location());
        company.setLogoUrl(request.logoUrl());

        return company;
    }

    public void updateEntity(Company company, UpdateCompanyRequest request) {
        company.setName(request.name());
        company.setDescription(request.description());
        company.setIndustry(request.industry());
        company.setWebsite(request.website());
        company.setLocation(request.location());
        company.setLogoUrl(request.logoUrl());
    }

    public CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getIndustry(),
                company.getWebsite(),
                company.getLocation(),
                company.getLogoUrl(),
                company.getCreatedAt(),
                company.getUpdatedAt()
        );
    }
}