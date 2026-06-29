package com.careerflow.vacancy;

import com.careerflow.company.Company;
import com.careerflow.company.CompanyRepository;
import com.careerflow.user.CurrentUserService;
import com.careerflow.user.User;
import com.careerflow.vacancy.dto.CreateVacancyRequest;
import com.careerflow.vacancy.dto.UpdateVacancyRequest;
import com.careerflow.vacancy.dto.VacancyResponse;
import com.careerflow.vacancy.enums.VacancyStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final CompanyRepository companyRepository;
    private final CurrentUserService currentUserService;
    private final VacancyMapper vacancyMapper;

    @Override
    public VacancyResponse create(CreateVacancyRequest request) {
        validateSalary(request.salaryMin(), request.salaryMax());

        Company company = getCurrentUserCompany();

        Vacancy vacancy = vacancyMapper.toEntity(request, company);
        Vacancy savedVacancy = vacancyRepository.save(vacancy);

        return vacancyMapper.toResponse(savedVacancy);
    }

    @Override
    public List<VacancyResponse> getAllActive() {
        return vacancyRepository.findByStatus(VacancyStatus.ACTIVE)
                .stream()
                .map(vacancyMapper::toResponse)
                .toList();
    }

    @Override
    public VacancyResponse getById(UUID id) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        return vacancyMapper.toResponse(vacancy);
    }

    @Override
    public List<VacancyResponse> getMyCompanyVacancies() {
        Company company = getCurrentUserCompany();

        return vacancyRepository.findByCompany(company)
                .stream()
                .map(vacancyMapper::toResponse)
                .toList();
    }

    @Override
    public VacancyResponse update(UUID id, UpdateVacancyRequest request) {
        validateSalary(request.salaryMin(), request.salaryMax());

        Company company = getCurrentUserCompany();

        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        validateVacancyOwnership(vacancy, company);

        vacancyMapper.updateEntity(vacancy, request);

        Vacancy updatedVacancy = vacancyRepository.save(vacancy);

        return vacancyMapper.toResponse(updatedVacancy);
    }

    @Override
    public void delete(UUID id) {
        Company company = getCurrentUserCompany();

        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        validateVacancyOwnership(vacancy, company);

        vacancy.setStatus(VacancyStatus.CLOSED);
        vacancyRepository.save(vacancy);
    }

    private Company getCurrentUserCompany() {
        User currentUser = currentUserService.getCurrentUser();

        List<Company> companies = companyRepository.findAllByUser(currentUser);

        if (companies.isEmpty()) {
            throw new RuntimeException("Company not found for current user");
        }

        if (companies.size() > 1) {
            throw new RuntimeException("Current user has more than one company");
        }

        return companies.get(0);
    }

    private void validateVacancyOwnership(Vacancy vacancy, Company company) {
        if (!vacancy.getCompany().getId().equals(company.getId())) {
            throw new RuntimeException("You are not allowed to manage this vacancy");
        }
    }

    private void validateSalary(BigDecimal salaryMin, BigDecimal salaryMax) {
        if (salaryMin != null && salaryMax != null && salaryMin.compareTo(salaryMax) > 0) {
            throw new RuntimeException("Minimum salary cannot be greater than maximum salary");
        }
    }
}