package com.example.cslink.user.cosmetologist.model.dto;

import java.util.List;

import com.example.cslink.management.schedule.model.ds.WorkingHours;
import com.example.cslink.management.schedule.model.entity.Availability;

public class CosmetologistDto {
    private Long id;
    private Long userProfileId;
    private List<Long> procedureIds;
    private String specialty;
    private List<Availability> availability;
    private Integer yearsOfExperience;

    public CosmetologistDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId=userProfileId;
    }

    public List<Long> getProcedureIds() {
        return procedureIds;
    }

    public void setProcedureIds(List<Long> procedureIds) {
        this.procedureIds=procedureIds;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability=availability;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty=specialty;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience=yearsOfExperience;
    }
}

