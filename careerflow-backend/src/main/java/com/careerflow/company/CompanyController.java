package com.careerflow.company;

import com.careerflow.company.dto.CompanyResponse;
import com.careerflow.company.dto.CreateCompanyRequest;
import com.careerflow.company.dto.UpdateCompanyRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAllByCurrentUser() {
        return companyService.getAllByCurrentUser();
    }

    @GetMapping("/{id}")
    public CompanyResponse getById(@PathVariable UUID id) {
        return companyService.getById(id);
    }

    @PostMapping
    public CompanyResponse create(@Valid @RequestBody CreateCompanyRequest request) {
        return companyService.create(request);
    }

    @PutMapping("/{id}")
    public CompanyResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCompanyRequest request
    ) {
        return companyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        companyService.delete(id);
    }
}