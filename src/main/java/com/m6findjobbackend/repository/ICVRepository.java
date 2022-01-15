package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICVRepository extends JpaRepository<CV,Long> {
    Optional<CV> findByUserId(Long id);
}
