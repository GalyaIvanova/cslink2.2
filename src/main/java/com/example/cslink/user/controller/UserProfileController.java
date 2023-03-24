package com.example.cslink.user.controller;

import java.net.URI;

import com.example.cslink.management.authentication.config.JwtTokenUtil;
import com.example.cslink.user.model.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cslink.user.controller.service.UserProfileService;
import com.example.cslink.user.model.dto.UserProfileDto;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUserProfileById(@PathVariable Long id) {
        UserProfileDto userProfileDTO = userProfileService.getUserProfileById(id);
        return ResponseEntity.ok().body(userProfileDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserProfileDto> loginUser(@RequestBody UserProfile userProfile) {
        //TODO JWT

        return ResponseEntity.ok().body(userProfileService.validateUserProfile(userProfile));
    }

    @PostMapping("/create")
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDTO) {
        UserProfileDto savedUserProfile = userProfileService.createUserProfile(userProfileDTO);
        return ResponseEntity.created(URI.create("/user-profiles/" + savedUserProfile.getId()))
                .body(savedUserProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileDto userProfileDTO) {
        UserProfileDto updatedUserProfile = userProfileService.updateUserProfile(id, userProfileDTO);
        return ResponseEntity.ok().body(updatedUserProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}

