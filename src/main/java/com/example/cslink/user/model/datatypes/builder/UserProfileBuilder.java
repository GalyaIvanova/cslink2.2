package com.example.cslink.user.model.datatypes.builder;

import com.example.cslink.user.model.datatypes.enums.Role;
import com.example.cslink.user.model.entity.UserProfile;

public class UserProfileBuilder {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Role role;
    private String token;

    public UserProfileBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserProfileBuilder withName(String firstName) {
        this.name = firstName;
        return this;
    }

    public UserProfileBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserProfileBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserProfileBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public UserProfileBuilder withToken(String token) {
        this.token = token;
        return this;
    }

    public UserProfile build() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        userProfile.setName(name);
        userProfile.setUsername(username);
        userProfile.setPassword(password);
        userProfile.setRole(role);
        userProfile.setToken(token);
        return userProfile;
    }
}
