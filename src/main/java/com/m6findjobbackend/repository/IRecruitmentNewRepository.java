package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.RecuitmentNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IRecruitmentNewRepository extends JpaRepository<RecuitmentNew, Long> {
    List<RecuitmentNew> findAllByCompany_Id(Long id);

    @Query("SELECT COUNT(r) FROM RecuitmentNew r")
    Long countTotalRecords();
}

