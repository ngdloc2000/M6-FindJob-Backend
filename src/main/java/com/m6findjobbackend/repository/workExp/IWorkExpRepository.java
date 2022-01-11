package com.m6findjobbackend.repository.workExp;

import com.m6findjobbackend.model.WorkExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkExpRepository extends JpaRepository<WorkExp,Long> {
}
