package com.example.cslink.management.schedule.model.entity;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;

import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import com.example.cslink.management.schedule.model.ds.WorkingHours;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"workingHours"}))
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private WorkingHours workingHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cosmetologist_id")
    private Cosmetologist cosmetologist_id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Add this field to store the last time the task was run
    private LocalDateTime lastTaskRun;

    public Availability() {
        this.createdAt = LocalDateTime.now();
        this.lastTaskRun = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Add a method with the @Scheduled annotation to define the scheduled task
    @Scheduled(fixedRate = 360000*24) // Runs every day
    public void scheduledTask() {
        System.out.println("Running scheduled task for entity with ID " + id);
        // TODO: Add your task logic here
        if (workingHours.getDate().isBefore(LocalDateTime.now().toLocalDate()) ){
           // entityManager.remove(this);
        }
        lastTaskRun = LocalDateTime.now();
    }
}
