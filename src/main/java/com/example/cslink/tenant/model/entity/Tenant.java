package com.example.cslink.tenant.model.entity;

import com.example.cslink.user.model.entity.UserProfile;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfile> userProfiles) {
        this.userProfiles=userProfiles;
    }

    public void addUserProfile(UserProfile userProfiles){
        this.userProfiles.add(userProfiles);
    }

    public boolean removeUserProfile(UserProfile userProfiles){
        return this.userProfiles.remove(userProfiles);
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tenant tenant=(Tenant) o;
        return id.equals(tenant.id)
                && name.equals(tenant.name)
                && domain.equals(tenant.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, domain);
    }
}

