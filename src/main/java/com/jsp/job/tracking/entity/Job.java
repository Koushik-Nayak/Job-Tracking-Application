package com.jsp.job.tracking.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String jobTitle;

    private String location;
    private String experience;
    private String salary;
    private String jobType;

    @Column(length = 3000)
    private String description;

    private String jobUrl;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDate;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private boolean active = true;
}
