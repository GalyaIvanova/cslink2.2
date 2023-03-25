package com.example.cslink.management.schedule.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.scheduling.annotation.Scheduled;

import com.example.cslink.user.model.entity.Cosmetologist;
import com.example.cslink.management.schedule.model.valueobject.WorkingHours;

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

    public Availability(WorkingHours workingHours) {
        this.workingHours=workingHours;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setId(Long id) {
        this.id=id;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(WorkingHours workingHours) {
        this.workingHours=workingHours;
    }

    public Cosmetologist getCosmetologist_id() {
        return cosmetologist_id;
    }

    public void setCosmetologist_id(Cosmetologist cosmetologist_id) {
        this.cosmetologist_id=cosmetologist_id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt=createdAt;
    }

    public LocalDateTime getLastTaskRun() {
        return lastTaskRun;
    }

    public void setLastTaskRun(LocalDateTime lastTaskRun) {
        this.lastTaskRun=lastTaskRun;
    }

    // Add a method with the @Scheduled annotation to define the scheduled task
    @Scheduled(fixedRate = 360000*24) // Runs every day
    public void scheduledTask() {
        System.out.println("Running scheduled task for entity with ID " + id);
        // TODO: Add logic here
        if (workingHours.getDate().isBefore(LocalDateTime.now().toLocalDate()) ){
           // entityManager.remove(this);
        }
        lastTaskRun = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availability that=(Availability) o;
        return Objects.equals(id, that.id) && Objects.equals(workingHours, that.workingHours) && Objects.equals(cosmetologist_id, that.cosmetologist_id) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workingHours, cosmetologist_id, createdAt);
    }

}
