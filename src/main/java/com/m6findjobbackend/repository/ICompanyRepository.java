package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findAllByAccount_Id(Long id);
    Boolean existsByName(String name);
}
