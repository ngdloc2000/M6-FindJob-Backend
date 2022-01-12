package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.RecuitmentNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecruitmentNewRepository extends JpaRepository<RecuitmentNew,Long> {
}
