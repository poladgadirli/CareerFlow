package com.careerflow.company;

import com.careerflow.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

    List<Company> findAllByUser(User user);

    boolean existsByNameAndUser(String name, User user);
}