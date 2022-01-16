package com.m6findjobbackend.dto.request;

import com.m6findjobbackend.model.CV;
import com.m6findjobbackend.model.Skill;
import com.m6findjobbackend.model.WorkExp;

import java.util.List;

public class CvDTO {
    private CV cv;
    private List<Skill> skills;
    private List<WorkExp> workExps;

    public CvDTO(CV cv, List<Skill> skills, List<WorkExp> workExps) {
        this.cv = cv;
        this.skills = skills;
        this.workExps = workExps;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<WorkExp> getWorkExps() {
        return workExps;
    }

    public void setWorkExps(List<WorkExp> workExps) {
        this.workExps = workExps;
    }
}
