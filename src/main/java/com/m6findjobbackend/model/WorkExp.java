package com.m6findjobbackend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workexp")
public class WorkExp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = CV.class)
    private CV cv;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String content;
}
