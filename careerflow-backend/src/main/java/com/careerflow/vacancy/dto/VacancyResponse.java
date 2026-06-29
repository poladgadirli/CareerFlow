package com.careerflow.vacancy.dto;

import com.careerflow.vacancy.enums.EmploymentType;
import com.careerflow.vacancy.enums.VacancyStatus;
import com.careerflow.vacancy.enums.WorkType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VacancyResponse(
        Long id,
        String title,
        String description,
        String requirements,
        String location,
        BigDecimal salaryMin,
        BigDecimal salaryMax,
        WorkType workType,
        EmploymentType employmentType,
        VacancyStatus status,
        Long companyId,
        String companyName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}