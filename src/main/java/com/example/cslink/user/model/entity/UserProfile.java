package com.example.cslink.user.model.entity;

import com.example.cslink.tenant.model.entity.Tenant;
import com.example.cslink.user.model.datatypes.enums.Role;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name="user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    private String password;
    private String token;
    private Role role;
    private String name;
    private String email;
    private String gender;

    @Embedded
    private Phone phone;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tenant_id")
    private Tenant tenant;

    public UserProfile() {

    }

    public UserProfile(Long id, @NonNull String username, String password, String token, Role role, String name, String email, String gender, Phone phone, Tenant tenant) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.token=token;
        this.role=role;
        this.name=name;
        this.email=email;
        this.gender=gender;
        this.phone=phone;
        this.tenant=tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role=role;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username=username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token=token;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender=gender;
    }
}

