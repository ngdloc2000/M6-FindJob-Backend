package com.m6findjobbackend.service.apply;

import com.m6findjobbackend.model.Apply;
import com.m6findjobbackend.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IApplyService  extends IGeneralService<Apply> {
    Page<Apply> findAllByUserId(Pageable pageable, Long id);
}
