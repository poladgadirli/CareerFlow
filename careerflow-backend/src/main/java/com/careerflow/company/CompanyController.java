package com.careerflow.company;

import com.careerflow.common.BaseResponse;
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
    public BaseResponse<List<CompanyResponse>> getAllByCurrentUser() {
        return BaseResponse.success(
                "Companies retrieved successfully",
                companyService.getAllByCurrentUser()
        );
    }

    @GetMapping("/{id}")
    public BaseResponse<CompanyResponse> getById(@PathVariable UUID id) {
        return BaseResponse.success(
                "Company retrieved successfully",
                companyService.getById(id)
        );
    }

    @PostMapping
    public BaseResponse<CompanyResponse> create(@Valid @RequestBody CreateCompanyRequest request) {
        return BaseResponse.success(
                "Company created successfully",
                companyService.create(request)
        );
    }

    @PutMapping("/{id}")
    public BaseResponse<CompanyResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCompanyRequest request
    ) {
        return BaseResponse.success(
                "Company updated successfully",
                companyService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable UUID id) {
        companyService.delete(id);

        return BaseResponse.success(
                "Company deleted successfully",
                null
        );
    }
}