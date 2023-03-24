package com.example.cslink.user.customer.model.dto;

import java.util.List;

public class CustomerDTO {
    private Long id;
    private Long userProfileId;

    private List<Long> cosmetologistIds;

    public CustomerDTO() {
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


}

