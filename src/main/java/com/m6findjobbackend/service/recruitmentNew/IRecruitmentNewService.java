package com.m6findjobbackend.service.recruitmentNew;

import com.m6findjobbackend.model.RecuitmentNew;
import com.m6findjobbackend.service.IGeneralService;

import java.util.List;

public interface IRecruitmentNewService extends IGeneralService<RecuitmentNew> {
    List<RecuitmentNew> findByNameCityAndNameField(String nameCity, String nameField);
    List<RecuitmentNew> findAllByCompany_Id(Long id);
}
