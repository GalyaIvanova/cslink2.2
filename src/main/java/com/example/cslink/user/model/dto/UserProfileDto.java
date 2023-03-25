package com.example.cslink.user.model.dto;

import com.example.cslink.user.model.datatypes.enums.Role;
import com.example.cslink.user.model.entity.Phone;

import java.util.Objects;

public class UserProfileDto {
    private Long id;
    private Role role;
    private String username;
    private String name;
    private String email;
    private Phone phone;
    private String password;
    private String token;
    private String gender;

    public UserProfileDto() {
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

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone=phone;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileDto that=(UserProfileDto) o;
        return Objects.equals(id, that.id)
                && role == that.role
                && Objects.equals(username, that.username)
                && Objects.equals(name, that.name)
                && Objects.equals(email, that.email)
                && Objects.equals(phone, that.phone)
                && Objects.equals(password, that.password)
                && Objects.equals(token, that.token)
                && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, username, name, email, phone, password, token, gender);
    }
}
