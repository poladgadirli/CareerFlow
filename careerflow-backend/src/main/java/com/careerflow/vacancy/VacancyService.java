package com.careerflow.vacancy;

import com.careerflow.vacancy.dto.CreateVacancyRequest;
import com.careerflow.vacancy.dto.UpdateVacancyRequest;
import com.careerflow.vacancy.dto.VacancyResponse;

import java.util.List;
import java.util.UUID;

public interface VacancyService {

    VacancyResponse create(CreateVacancyRequest request);

    List<VacancyResponse> getAllActive();

    VacancyResponse getById(UUID id);

    List<VacancyResponse> getMyCompanyVacancies();

    VacancyResponse update(UUID id, UpdateVacancyRequest request);

    void delete(UUID id);
}