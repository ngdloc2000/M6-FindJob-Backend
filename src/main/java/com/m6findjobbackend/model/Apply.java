package com.m6findjobbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "apply")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = RecuitmentNew.class)
    private RecuitmentNew recuitmentNew;
    @Enumerated(EnumType.STRING)
    @NaturalId
    private Status status;
}
