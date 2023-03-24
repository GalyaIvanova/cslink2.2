package com.example.cslink.user.controller.service;

import java.util.List;

import com.example.cslink.user.model.dto.UserProfileDto;
import com.example.cslink.user.model.entity.UserProfile;

public interface UserProfileService {

    UserProfileDto getUserProfileById(Long id);

    UserProfileDto validateUserProfile(UserProfile userProfile);

    UserProfileDto createUserProfile(UserProfileDto userProfile);

    UserProfileDto updateUserProfile(Long id, UserProfileDto userProfileDetails);

    void deleteUserProfile(Long id);

    List<UserProfileDto> getAllUserProfiles();
}

