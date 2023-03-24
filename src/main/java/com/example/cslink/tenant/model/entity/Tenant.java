package com.example.cslink.tenant.model.entity;

import com.example.cslink.user.model.entity.UserProfile;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false)
    private String domain;

    @OneToMany(mappedBy="tenant")
    private List<UserProfile> userProfiles;

    public Tenant() {
    }

    public Tenant(String name, String domain) {
        this.name=name;
        this.domain=domain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain=domain;
    }


}

