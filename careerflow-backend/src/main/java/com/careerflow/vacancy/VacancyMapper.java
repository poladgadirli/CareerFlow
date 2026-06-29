package com.careerflow.vacancy;

import com.careerflow.company.Company;
import com.careerflow.vacancy.dto.CreateVacancyRequest;
import com.careerflow.vacancy.dto.UpdateVacancyRequest;
import com.careerflow.vacancy.dto.VacancyResponse;
import com.careerflow.vacancy.enums.VacancyStatus;
import org.springframework.stereotype.Component;

@Component
public class VacancyMapper {

    public Vacancy toEntity(CreateVacancyRequest request, Company company) {
        Vacancy vacancy = new Vacancy();

        vacancy.setTitle(request.title());
        vacancy.setDescription(request.description());
        vacancy.setRequirements(request.requirements());
        vacancy.setLocation(request.location());
        vacancy.setSalaryMin(request.salaryMin());
        vacancy.setSalaryMax(request.salaryMax());
        vacancy.setWorkType(request.workType());
        vacancy.setEmploymentType(request.employmentType());
        vacancy.setStatus(VacancyStatus.ACTIVE);
        vacancy.setCompany(company);

        return vacancy;
    }

    public void updateEntity(Vacancy vacancy, UpdateVacancyRequest request) {
        vacancy.setTitle(request.title());
        vacancy.setDescription(request.description());
        vacancy.setRequirements(request.requirements());
        vacancy.setLocation(request.location());
        vacancy.setSalaryMin(request.salaryMin());
        vacancy.setSalaryMax(request.salaryMax());
        vacancy.setWorkType(request.workType());
        vacancy.setEmploymentType(request.employmentType());
        vacancy.setStatus(request.status());
    }

    public VacancyResponse toResponse(Vacancy vacancy) {
        Company company = vacancy.getCompany();

        return new VacancyResponse(
                vacancy.getId(),
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getRequirements(),
                vacancy.getLocation(),
                vacancy.getSalaryMin(),
                vacancy.getSalaryMax(),
                vacancy.getWorkType(),
                vacancy.getEmploymentType(),
                vacancy.getStatus(),
                company.getId(),
                company.getName(),
                vacancy.getCreatedAt(),
                vacancy.getUpdatedAt()
        );
    }
}