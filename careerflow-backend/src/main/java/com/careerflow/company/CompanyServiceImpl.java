package com.careerflow.company;

import com.careerflow.company.dto.CompanyResponse;
import com.careerflow.company.dto.CreateCompanyRequest;
import com.careerflow.company.dto.UpdateCompanyRequest;
import com.careerflow.user.User;
import com.careerflow.user.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CurrentUserService currentUserService;

    @Override
    public List<CompanyResponse> getAllByCurrentUser() {
        User currentUser = currentUserService.getCurrentUser();

        return companyRepository.findAllByUser(currentUser)
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }

    @Override
    public CompanyResponse getById(UUID id) {
        User currentUser = currentUserService.getCurrentUser();

        Company company = companyRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return companyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse create(CreateCompanyRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        if (companyRepository.existsByNameAndUser(request.name(), currentUser)) {
            throw new RuntimeException("Company already exists");
        }

        Company company = companyMapper.toEntity(request);
        company.setUser(currentUser);

        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponse(savedCompany);
    }

    @Override
    public CompanyResponse update(UUID id, UpdateCompanyRequest request) {
        User currentUser = currentUserService.getCurrentUser();

        Company company = companyRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyMapper.updateEntity(company, request);

        Company updatedCompany = companyRepository.save(company);

        return companyMapper.toResponse(updatedCompany);
    }

    @Override
    public void delete(UUID id) {
        User currentUser = currentUserService.getCurrentUser();

        Company company = companyRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.delete(company);
    }
}