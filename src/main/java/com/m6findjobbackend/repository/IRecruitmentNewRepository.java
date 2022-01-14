package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.RecuitmentNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecruitmentNewRepository extends JpaRepository<RecuitmentNew, Long> {
    List<RecuitmentNew> findByOrderByIdDesc();

    List<RecuitmentNew> findAllByCompany_Id(Long id);
}
