package com.careerflow.company;

import com.careerflow.company.dto.CompanyResponse;
import com.careerflow.company.dto.CreateCompanyRequest;
import com.careerflow.company.dto.UpdateCompanyRequest;

import java.util.List;
import java.util.UUID;

public interface CompanyService {

    List<CompanyResponse> getAllByCurrentUser();

    CompanyResponse getById(UUID id);

    CompanyResponse create(CreateCompanyRequest request);

    CompanyResponse update(UUID id, UpdateCompanyRequest request);

    void delete(UUID id);
}