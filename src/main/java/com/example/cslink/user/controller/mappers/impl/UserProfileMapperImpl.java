package com.example.cslink.user.controller.mappers.impl;

import org.springframework.stereotype.Component;

import com.example.cslink.user.controller.mappers.UserProfileMapper;
import com.example.cslink.user.model.dto.UserProfileDTO;
import com.example.cslink.user.model.entity.UserProfile;

@Component
public class UserProfileMapperImpl implements UserProfileMapper {
    @Override
    public UserProfileDTO toDtoModel(UserProfile entity) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(entity.getId());
        userProfileDTO.setRole(entity.getRole());
        userProfileDTO.setName(entity.getName());
        userProfileDTO.setPhone(entity.getPhone());
        userProfileDTO.setEmail(entity.getEmail());

        return userProfileDTO;
    }

    @Override
    public UserProfile toEntity(UserProfileDTO dto) {
        UserProfile entity = new UserProfile();
        entity.setId(dto.getId());
        entity.setRole(dto.getRole());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}