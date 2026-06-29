package com.careerflow.vacancy.dto;

import com.careerflow.vacancy.enums.EmploymentType;
import com.careerflow.vacancy.enums.VacancyStatus;
import com.careerflow.vacancy.enums.WorkType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateVacancyRequest(

        @NotBlank(message = "Title is required")
        @Size(max = 150, message = "Title must be at most 150 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 5000, message = "Description must be at most 5000 characters")
        String description,

        @Size(max = 5000, message = "Requirements must be at most 5000 characters")
        String requirements,

        @NotBlank(message = "Location is required")
        @Size(max = 150, message = "Location must be at most 150 characters")
        String location,

        @DecimalMin(value = "0.0", inclusive = true, message = "Minimum salary cannot be negative")
        BigDecimal salaryMin,

        @DecimalMin(value = "0.0", inclusive = true, message = "Maximum salary cannot be negative")
        BigDecimal salaryMax,

        @NotNull(message = "Work type is required")
        WorkType workType,

        @NotNull(message = "Employment type is required")
        EmploymentType employmentType,

        @NotNull(message = "Vacancy status is required")
        VacancyStatus status
) {
}