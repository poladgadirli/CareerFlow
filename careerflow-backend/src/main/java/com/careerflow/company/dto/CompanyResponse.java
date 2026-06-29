package com.careerflow.company.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyResponse(
        UUID id,
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