package com.careerflow.vacancy;

import com.careerflow.company.Company;
import com.careerflow.vacancy.enums.VacancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findByStatus(VacancyStatus status);

    List<Vacancy> findByCompany(Company company);

    List<Vacancy> findByCompanyAndStatus(Company company, VacancyStatus status);
}