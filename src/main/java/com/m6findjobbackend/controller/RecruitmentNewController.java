package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.RecuitmentNew;
import com.m6findjobbackend.model.Status;
import com.m6findjobbackend.service.city.CityService;
import com.m6findjobbackend.service.company.CompanyService;
import com.m6findjobbackend.service.field.FieldService;
import com.m6findjobbackend.service.recruitmentNew.RecruitmentNewService;
import com.m6findjobbackend.service.vacancies.VacanciesService;
import com.m6findjobbackend.service.workingTime.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("recruitment")
public class RecruitmentNewController {
    @Autowired
    RecruitmentNewService recruitmentNewService;

//    @Autowired
//    WorkingTimeService workingTimeService;

//    @Autowired
//    FieldService fieldService;
//
//    @Autowired
//    CompanyService companyService;

//    @Autowired
//    VacanciesService vacanciesService;
//
//    @Autowired
//    CityService cityService;

    @GetMapping("/list")
    public ResponseEntity<?> showListRecruitmentNew(){
        List<RecuitmentNew> recuitmentNewList = (List<RecuitmentNew>) recruitmentNewService.findAll();
        if (recuitmentNewList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recuitmentNewList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRecruitmentNew(@RequestBody RecuitmentNew recuitmentNew){
        if (recuitmentNew.getQuantity() == Integer.parseInt(null)) {
            return new ResponseEntity<>(new ResponseMessage("no_quantity"), HttpStatus.OK);
        }
        //tao codeCompany
        String nameex = recuitmentNew.getTitle().substring(0, 3);
        String nameCompany = recuitmentNew.getCompany().getCodeCompany();
        recuitmentNew.setCodeNews(nameex + nameCompany + recuitmentNew.getId());
        System.out.println(recuitmentNew.getCodeNews());
        recuitmentNew.setStatus(Status.UNLOCK);

        recruitmentNewService.save(recuitmentNew);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

}
