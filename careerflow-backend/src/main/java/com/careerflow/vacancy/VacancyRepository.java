package com.careerflow.vacancy;

import com.careerflow.company.Company;
import com.careerflow.vacancy.enums.VacancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VacancyRepository extends JpaRepository<Vacancy, UUID> {

    List<Vacancy> findByStatus(VacancyStatus status);

    List<Vacancy> findByCompany(Company company);

    List<Vacancy> findByCompanyAndStatus(Company company, VacancyStatus status);
}