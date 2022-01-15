package com.m6findjobbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cv")
@Data
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private int expYear;
    private Double salaryExpectation;
    private String fileCV;
    @OneToMany(targetEntity = Skill.class, mappedBy = "cv")
    @JsonIgnore
    private List<Skill> skills;
    @OneToMany(targetEntity = WorkExp.class, mappedBy = "cv")
    @JsonIgnore
    private List<WorkExp> workExps;
}
