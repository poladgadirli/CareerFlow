package com.careerflow.company;

import com.careerflow.company.dto.CompanyResponse;
import com.careerflow.company.dto.CreateCompanyRequest;
import com.careerflow.company.dto.UpdateCompanyRequest;
import com.careerflow.user.User;
import com.careerflow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserRepository userRepository;

    @Override
    public List<CompanyResponse> getAllByCurrentUser() {
        User currentUser = getCurrentUser();

        return companyRepository.findAllByUser(currentUser)
                .stream()
                .map(companyMapper::toResponse)
                .toList();
    }

    @Override
    public CompanyResponse getById(Long id) {
        User currentUser = getCurrentUser();

        Company company = companyRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return companyMapper.toResponse(company);
    }

    @Override
    public CompanyResponse create(CreateCompanyRequest request) {
        User currentUser = getCurrentUser();

        if (companyRepository.existsByNameAndUser(request.name(), currentUser)) {
            throw new RuntimeException("Company already exists");
        }

        Company company = companyMapper.toEntity(request);
        company.setUser(currentUser);

        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponse(savedCompany);
    }

    @Override
    public CompanyResponse update(Long id, UpdateCompanyRequest request) {
        User currentUser = getCurrentUser();

        Company company = companyRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyMapper.updateEntity(company, request);

        Company updatedCompany = companyRepository.save(company);

        return companyMapper.toResponse(updatedCompany);
    }

    @Override
    public void delete(Long id) {
        User currentUser = getCurrentUser();

        Company company = companyRepository.findById(id)
                .filter(c -> c.getUser().getId().equals(currentUser.getId()))
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.delete(company);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}