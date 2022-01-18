package com.m6findjobbackend.controller;

import com.m6findjobbackend.dto.request.ApplyJob;
import com.m6findjobbackend.dto.request.ForwardApply;
import com.m6findjobbackend.dto.response.ResponseMessage;
import com.m6findjobbackend.model.*;
import com.m6findjobbackend.model.email.MailObject;
import com.m6findjobbackend.service.CV.ICVService;
import com.m6findjobbackend.service.account.AccountService;
import com.m6findjobbackend.service.apply.IApplyService;
import com.m6findjobbackend.service.company.CompanyService;
import com.m6findjobbackend.service.email.EmailService;
import com.m6findjobbackend.service.email.EmailServiceImpl;
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
    private CompanyService companyService;

    @Autowired
    private ICVService icvService;

    @Autowired
    private EmailServiceImpl emailService;

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
    @PostMapping("/forward")
    public ResponseEntity<?> forwardApply(@RequestBody ForwardApply forwardApply){
        Company companyCurrent = companyService.findById(forwardApply.getIdCompany()).get();
        User userCurrent = userSevice.findById(forwardApply.getIdUser()).get();
        MailObject mailObject = new MailObject("findJob@job.com",userCurrent.getAccount().getUsername(), "Thông báo tuyển dụng", "Bạn " + userCurrent.getName() + " đã ứng tuyển vào công ty " +companyCurrent.getName() + " thành công. Vui lòng chờ mail từ công ty để xác nhận" );
        emailService.sendSimpleMessage(mailObject);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }
    @PostMapping("/forwardSuccess")
    public ResponseEntity<?> forwardApplySuccess(@RequestBody ForwardApply forwardApply){
        Company companyCurrent = companyService.findById(forwardApply.getIdCompany()).get();
        User userCurrent = userSevice.findById(forwardApply.getIdUser()).get();
        LocalDate dateApply = LocalDate.now().plusDays(3) ;
        MailObject mailObject = new MailObject("findJob@job.com",userCurrent.getAccount().getUsername(), "Thông báo tuyển dụng", "Công ty " + companyCurrent.getName() + " đã chấp nhận đơn ứng tuyển của bạn " + userCurrent.getName() + ". Lịch phỏng vấn của bạn với công ty vào ngày " + dateApply +". Hãy liên hệ với công ty bạn ứng tuyển để biết thêm chi tiết !!");
        emailService.sendSimpleMessage(mailObject);
        return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
    }

}
