package com.example.cslink.user.controller.service.impl;

import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.exceptions.ResourceAlreadyExistsException;
import com.example.cslink.management.authentication.config.JwtTokenUtil;
import com.example.cslink.user.controller.mappers.UserProfileMapper;
import com.example.cslink.user.controller.service.UserProfileService;
import com.example.cslink.user.model.dataccess.dao.UserProfileDao;
import com.example.cslink.user.model.dto.UserProfileDto;
import com.example.cslink.user.model.entity.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao userProfileRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil=new JwtTokenUtil();

    @Override
    public UserProfileDto getUserProfileById(Long id) {
        UserProfile userProfile=userProfileRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("UserProfile", id));
        return userProfileMapper.toDtoModel(userProfile);
    }

    @Override
    public UserProfileDto validateUserProfile(UserProfile userProfile) {
        Optional<UserProfile> userProfileExpected=userProfileRepository.findByUsername(userProfile.getUsername());

        if (userProfileExpected.isPresent()) {
            Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
            grantedAuthorities.add(userProfile.getRole());
            User userDetails=new User(userProfile.getUsername(), getEncodedPassword(userProfile.getPassword()),
                    grantedAuthorities);

            if (jwtTokenUtil.validateToken(userProfile.getToken(), userDetails)) {
                userProfile.setToken(jwtTokenUtil.generateToken(userDetails));
            }
        } else {
            throw new CustomResourceNotFoundException("UserProfile" + userProfile.getUsername());
        }

        return userProfileMapper.toDtoModel(userProfile);
    }

    @Override
    public UserProfileDto createUserProfile(UserProfileDto userProfile) {
        UserProfile entity=userProfileMapper.toEntity(userProfile);
        entity.setPassword(getEncodedPassword(entity.getPassword()));
        Optional<UserProfile> profile=userProfileRepository.findByUsername(entity.getUsername());

        if (profile.isPresent()) {
            throw new ResourceAlreadyExistsException("User profile already exists: " + profile.get().getUsername());
        } else {
            return userProfileMapper.toDtoModel(userProfileRepository.save(entity));
        }
    }

    @Override
    public UserProfileDto updateUserProfile(Long id, UserProfileDto userProfileDetails) {
        userProfileRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("UserProfile", id));
        UserProfile userProfile=userProfileMapper.toEntity(userProfileDetails);
        userProfile.setId(id);
        return userProfileMapper.toDtoModel(userProfileRepository.save(userProfile));
    }

    @Override
    public void deleteUserProfile(Long id) {
        UserProfile userProfile=userProfileRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("UserProfile", id));
        userProfileRepository.delete(userProfile);
    }

    @Override
    public List<UserProfileDto> getAllUserProfiles() {
        return null;
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder().encode(password);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
