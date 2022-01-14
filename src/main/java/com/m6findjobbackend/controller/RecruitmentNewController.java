package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.RecuitmentNew;
import com.m6findjobbackend.model.Status;
import com.m6findjobbackend.service.recruitmentNew.RecruitmentNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> showListRecruitmentNew() {
        List<RecuitmentNew> recuitmentNewList = (List<RecuitmentNew>) recruitmentNewService.findAll();
        if (recuitmentNewList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recuitmentNewList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createRecruitmentNew(@RequestBody RecuitmentNew recuitmentNew) {
        if (recuitmentNew.getQuantity() == null) {
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

    @GetMapping("/{id}")
    public ResponseEntity<?> detailRecruitmentNew(@PathVariable Long id) {
        Optional<RecuitmentNew> recuitmentNew = recruitmentNewService.findById(id);
        if (!recuitmentNew.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recuitmentNew, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecruitmentNew(@PathVariable Long id) {
        Optional<RecuitmentNew> recuitmentNew = recruitmentNewService.findById(id);
        if (!recuitmentNew.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recruitmentNewService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecruitmentNew(@PathVariable Long id, @RequestBody RecuitmentNew recuitmentNew) {
        Optional<RecuitmentNew> recuitmentNew1 = recruitmentNewService.findById(id);
        if (!recuitmentNew1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (recuitmentNew.getQuantity() == null) {
            return new ResponseEntity<>(new ResponseMessage("no_quantity"), HttpStatus.OK);
        }
        if (recuitmentNew.getSalary() == null) {
            return new ResponseEntity<>(new ResponseMessage("no_salary"), HttpStatus.OK);
        }
        recuitmentNew1.get().setTitle(recuitmentNew.getTitle());
        recuitmentNew1.get().setWorkingTime(recuitmentNew.getWorkingTime());
        recuitmentNew1.get().setField(recuitmentNew.getField());
        recuitmentNew1.get().setVacancies(recuitmentNew.getVacancies());
        recuitmentNew1.get().setExpDate(recuitmentNew.getExpDate());
        recuitmentNew1.get().setDescription(recuitmentNew.getDescription());
        recuitmentNew1.get().setQuantity(recuitmentNew.getQuantity());
        recuitmentNew1.get().setSalary(recuitmentNew.getSalary());
        recuitmentNew1.get().setGender(recuitmentNew.getGender());
        recuitmentNew1.get().setCity(recuitmentNew.getCity());
        recruitmentNewService.save(recuitmentNew1.get());
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);

    }

    @GetMapping("/showAll/{id}")
    public ResponseEntity<?>findAllByCompany(@PathVariable Long id){
        return new ResponseEntity<>(recruitmentNewService.findAllByCompany_Id(id),HttpStatus.OK);
    }

    @GetMapping("/findRecuitmentNewest")
    public ResponseEntity<List<RecuitmentNew>> getRecuitmentNewest() {
        List<RecuitmentNew> list = this.recruitmentNewService.findByOrderByIdDesc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
