package com.careerflow.vacancy;

import com.careerflow.common.BaseResponse;
import com.careerflow.vacancy.dto.CreateVacancyRequest;
import com.careerflow.vacancy.dto.UpdateVacancyRequest;
import com.careerflow.vacancy.dto.VacancyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public BaseResponse<VacancyResponse> create(@Valid @RequestBody CreateVacancyRequest request) {
        VacancyResponse response = vacancyService.create(request);

        return BaseResponse.success("Vacancy created successfully", response);
    }

    @GetMapping
    public BaseResponse<List<VacancyResponse>> getAllActive() {
        List<VacancyResponse> responses = vacancyService.getAllActive();

        return BaseResponse.success("Active vacancies fetched successfully", responses);
    }

    @GetMapping("/{id}")
    public BaseResponse<VacancyResponse> getById(@PathVariable UUID id) {
        VacancyResponse response = vacancyService.getById(id);

        return BaseResponse.success("Vacancy fetched successfully", response);
    }

    @GetMapping("/company/me")
    public BaseResponse<List<VacancyResponse>> getMyCompanyVacancies() {
        List<VacancyResponse> responses = vacancyService.getMyCompanyVacancies();

        return BaseResponse.success("Company vacancies fetched successfully", responses);
    }

    @PutMapping("/{id}")
    public BaseResponse<VacancyResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateVacancyRequest request
    ) {
        VacancyResponse response = vacancyService.update(id, request);

        return BaseResponse.success("Vacancy updated successfully", response);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable UUID id) {
        vacancyService.delete(id);

        return BaseResponse.success("Vacancy deleted successfully", null);
    }
}