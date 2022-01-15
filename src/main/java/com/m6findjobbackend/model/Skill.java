package com.m6findjobbackend.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int proficiency;
    @ManyToOne(targetEntity = CV.class)
    private CV cv;
}
