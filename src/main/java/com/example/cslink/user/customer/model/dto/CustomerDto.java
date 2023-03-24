package com.example.cslink.user.customer.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDto {
    private Long id;
    private Long userProfileId;
    private List<Long> cosmetologistIds;
    private LocalDateTime createdAt;

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getCosmetologistIds() {
        return cosmetologistIds;
    }

    public void setCosmetologistIds(List<Long> cosmetologistIds) {
        this.cosmetologistIds = cosmetologistIds;
    }


    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt=createdAt;
    }
}

