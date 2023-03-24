package com.example.cslink.user.cosmetologist.model.dto;

import java.util.List;

import com.example.cslink.management.schedule.model.ds.WorkingHours;

public class CosmetologistDTO {
    private Long id;
    private Long userProfileId;
    private List<Long> procedureIds;
    private WorkingHours availability;

    public CosmetologistDTO() {}

    public CosmetologistDTO(Long id, Long userProfileId, List<Long> procedureIds, WorkingHours availability) {
        this.id=id;
        this.userProfileId=userProfileId;
        this.procedureIds=procedureIds;
        this.availability=availability;
    }

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

    public WorkingHours getAvailability() {
        return availability;
    }

    public void setAvailability(WorkingHours availability) {
        this.availability=availability;
    }
}

