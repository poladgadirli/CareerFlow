package com.careerflow.company;

import com.careerflow.company.dto.CompanyResponse;
import com.careerflow.company.dto.CreateCompanyRequest;
import com.careerflow.company.dto.UpdateCompanyRequest;

import java.util.List;

public interface CompanyService {

    List<CompanyResponse> getAllByCurrentUser();

    CompanyResponse getById(Long id);

    CompanyResponse create(CreateCompanyRequest request);

    CompanyResponse update(Long id, UpdateCompanyRequest request);

    void delete(Long id);
}