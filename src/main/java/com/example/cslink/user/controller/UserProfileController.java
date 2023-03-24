package com.example.cslink.user.controller;

import java.net.URI;

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
import com.example.cslink.user.model.dto.UserProfileDTO;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Long id) {
        UserProfileDTO userProfileDTO = userProfileService.getUserProfileById(id);
        return ResponseEntity.ok().body(userProfileDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<UserProfileDTO> loginUser(@RequestBody UserProfileDTO userProfileDTO) {
        //TODO JWT
        return ResponseEntity.ok().body(userProfileService.validateUserProfile(userProfileDTO));
    }

    @PostMapping("/create")
    public ResponseEntity<UserProfileDTO> createUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO savedUserProfile = userProfileService.createUserProfile(userProfileDTO);
        return ResponseEntity.created(URI.create("/user-profiles/" + savedUserProfile.getId()))
                .body(savedUserProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedUserProfile = userProfileService.updateUserProfile(id, userProfileDTO);
        return ResponseEntity.ok().body(updatedUserProfile);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return ResponseEntity.noContent().build();
    }
}

