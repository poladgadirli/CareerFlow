package com.careerflow.company.dto;

import java.time.LocalDateTime;

public record CompanyResponse(
        Long id,
        String name,
        String description,
        String industry,
        String website,
        String location,
        String logoUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}