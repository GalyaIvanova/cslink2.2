package com.example.cslink.user.controller.service.impl;

import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.user.controller.mappers.UserProfileMapper;
import com.example.cslink.user.controller.service.UserProfileService;
import com.example.cslink.user.model.dataccess.dao.UserProfileDao;
import com.example.cslink.user.model.dataccess.persistence.UserProfilePersistence;
import com.example.cslink.user.model.dto.UserProfileDTO;
import com.example.cslink.user.model.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserProfilePersistence UserProfilePersistence;


    //    @Override
    //    public UserProfileDTO createUserProfile(UserProfile userProfile) {
    //
    //    }

    //    @Override
    //    public UserProfileDTO getUserProfile(Long id) {
    //        return userProfileAssembler.toDtoModel(getUserProfileById(id));
    //    }
    //
    //    @Override
    //    public UserProfileDTO getUserProfileByUserId(Long userId) {
    //        return userProfileAssembler.toDtoModel(userProfileRepository.findByUserId(userId));
    //    }
    //
    //    @Override
    //    public void updateUserProfile(Long id, UserProfile userProfileDetails) {
    //        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("UserProfile",  id));
    //        userProfile.setName(userProfileDetails.getName());
    //        userProfile.setPhone(userProfileDetails.getPhone());
    ////        userProfile.setGender(userProfileDetails.getGender());
    //        userProfileRepository.save(userProfile);
    //    }

    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        UserProfile userProfile=userProfileRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("UserProfile", id));
        return userProfileMapper.toDtoModel(userProfile);
    }

    @Override
    public UserProfileDTO validateUserProfile(UserProfileDTO userProfile) {
        return userProfileMapper.toDtoModel(UserProfilePersistence.getExistingUser(userProfileMapper.toEntity(userProfile)));
    }

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfile) {

        UserProfile entity=userProfileMapper.toEntity(userProfile);
        entity.setPassword(getEncodedPassword(entity.getPassword()));
        return userProfileMapper.toDtoModel(userProfileRepository.save(entity));
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder().encode(password);
    }

    @Override
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDetails) {
        UserProfile userProfile=userProfileRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("UserProfile", id));
        userProfile.setName(userProfileDetails.getName());
        userProfile.setPhone(userProfileDetails.getPhone());
        //        userProfile.setGender(userProfileDetails.getGender());
        return userProfileMapper.toDtoModel(userProfileRepository.save(userProfile));

    }

    @Override
    public void deleteUserProfile(Long id) {
        UserProfile userProfile=userProfileRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("UserProfile", id));
        userProfileRepository.delete(userProfile);
    }

    @Override
    public List<UserProfileDTO> getAllUserProfiles() {
        return null;
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
