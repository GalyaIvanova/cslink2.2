package com.example.cslink.tenant.model.dto;

import com.example.cslink.user.model.dto.UserProfileDto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TenantDto tenantDto=(TenantDto) o;
        return Objects.equals(id, tenantDto.id)
                && Objects.equals(name, tenantDto.name)
                && Objects.equals(domain, tenantDto.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, domain);
    }

    @Override
    public String toString() {
        return "TenantDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
