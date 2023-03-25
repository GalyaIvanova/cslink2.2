package com.example.cslink.user.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that=(CustomerDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(userProfileId, that.userProfileId)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userProfileId, createdAt);
    }
}

