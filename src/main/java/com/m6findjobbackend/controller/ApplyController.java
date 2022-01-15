package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.request.ApplyJob;
import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.*;
import com.m6findjobbackend.service.CV.ICVService;
import com.m6findjobbackend.service.apply.IApplyService;
import com.m6findjobbackend.service.recruitmentNew.IRecruitmentNewService;
import com.m6findjobbackend.service.user.IUserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin("*")
@RequestMapping("/applies")
public class ApplyController {
    @Autowired
    private IApplyService applyService;

    @Autowired
    private IRecruitmentNewService recruitmentNewService;

    @Autowired
    private IUserSevice userSevice;

    @Autowired
    private ICVService icvService;

    @PostMapping
    public ResponseEntity<?> createApply(@RequestBody ApplyJob applyJob) {
        if(icvService.existsByUserId(applyJob.getUserId())){
            LocalDate now = LocalDate.now();
            RecuitmentNew recuitmentNew = recruitmentNewService.findById(applyJob.getRecuitmentNewId()).get();
            User user = userSevice.findById(applyJob.getUserId()).get();
            Apply apply = new Apply();
            apply.setDate(now);
            apply.setStatus(Status.WAIT);
            apply.setUser(user);
            apply.setRecuitmentNew(recuitmentNew);
            applyService.save(apply);
            return new ResponseEntity<>(new ResponseMessage("CREATE"), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(new ResponseMessage("CREATE_FAIL"), HttpStatus.OK);
        }
    }
}
