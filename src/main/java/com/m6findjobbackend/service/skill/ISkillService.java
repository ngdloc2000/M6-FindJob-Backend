package com.m6findjobbackend.service.skill;

import com.m6findjobbackend.model.Skill;
import com.m6findjobbackend.service.IGeneralService;

import java.util.Optional;

public interface ISkillService extends IGeneralService<Skill> {
    Iterable<Skill> findAllSkillsByCvId(Long id);
}
