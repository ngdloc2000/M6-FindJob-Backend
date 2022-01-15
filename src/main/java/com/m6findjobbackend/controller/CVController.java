package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.CV;
import com.m6findjobbackend.model.City;
import com.m6findjobbackend.model.Company;
import com.m6findjobbackend.service.CV.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RequestMapping("CV")
@RestController
public class CVController {
    @Autowired
    CVService cvService;

    @GetMapping("/showAll")
    public ResponseEntity<?> showAll() {
        return new ResponseEntity<>(cvService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createCV(@RequestBody CV cv){
        if(cv.getFileCV()==null){
            return new ResponseEntity<>(new ResponseMessage("no_file_cv"), HttpStatus.OK);
        }
        if(cv.getSalaryExpectation()==null){
            return new ResponseEntity<>(new ResponseMessage("no_file_cv"), HttpStatus.OK);
        }
        cvService.save(cv);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCV(@PathVariable Long id, @RequestBody CV cv){
        Optional<CV> cv1 = cvService.findById(id);
        if(!cv1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(cv.getFileCV()==null){
            return new ResponseEntity<>(new ResponseMessage("no_file_cv"), HttpStatus.OK);
        }
        if(cv.getSalaryExpectation()==null){
            return new ResponseEntity<>(new ResponseMessage("no_file_cv"), HttpStatus.OK);
        }

        cv1.get().setFileCV(cv.getFileCV());
        cv1.get().setExpYear(cv.getExpYear());
        cv1.get().setSalaryExpectation(cv.getSalaryExpectation());
        cvService.save(cv1.get());
        return new ResponseEntity<>(cv1.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCV(@PathVariable Long id) {
        Optional<CV> cv = cvService.findById(id);
        if (!cv.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cv, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCV(@PathVariable Long id) {
        Optional<CV> cv = cvService.findById(id);
        if (!cv.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cvService.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
}
