package com.careerflow.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCompanyRequest(

        @NotBlank(message = "Company name is required")
        @Size(max = 50, message = "Company name must be at most 50 characters")
        String name,

        @Size(max = 500, message = "Description must be at most 500 characters")
        String description,

        @Size(max = 50, message = "Industry must be at most 50 characters")
        String industry,

        @Size(max = 150, message = "Website must be at most 150 characters")
        String website,

        @Size(max = 150, message = "Location must be at most 150 characters")
        String location,

        @Size(max = 500, message = "Logo URL must be at most 500 characters")
        String logoUrl
) {
}