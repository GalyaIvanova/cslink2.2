package com.example.cslink.tenant.model.dto;

import com.example.cslink.user.model.dto.UserProfileDto;

import java.util.List;

public class TenantDto {

    private Long id;
    private String name;
    private String domain;
    private List<UserProfileDto> userProfiles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<UserProfileDto> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfileDto> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
