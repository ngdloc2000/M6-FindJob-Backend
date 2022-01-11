package com.m6findjobbackend.service.company;

import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.service.IGeneralService;

import java.util.Optional;

public interface ICompanyService extends IGeneralService<Company> {
    Optional<Company> findAllByAccount_Id(Long id);
    Boolean existsByName(String name);
}
