package com.m6findjobbackend.repository;

import com.m6findjobbackend.model.RecuitmentNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecruitmentNewRepository extends JpaRepository<RecuitmentNew,Long> {
   @Query("select r.company from RecuitmentNew r where (:name is null and r.city.name like %:name%) and (:field is null and r.field.name like %:field%)" )
   List<RecuitmentNew> findByNameCityAndNameField(@Param("name") String nameCity, @Param("field") String nameField);

}
