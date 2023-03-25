package com.example.cslink.user.model.datatypes.valueobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class SimpleUser {
    @JsonIgnore
    private Long id;
    private String username;
    private String password;
    @JsonIgnore
    private Long  userProfileId;

    public SimpleUser() {
    }


    public SimpleUser(Long id, String username, String password, Long userProfileId) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.userProfileId=userProfileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId=userProfileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUser that=(SimpleUser) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(userProfileId, that.userProfileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userProfileId);
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userProfileId=" + userProfileId +
                '}';
    }
}