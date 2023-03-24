package com.example.cslink.user.controller.mappers;

import com.example.cslink.user.model.dto.UserProfileDTO;
import com.example.cslink.user.model.entity.UserProfile;

public interface UserProfileMapper {
    UserProfileDTO toDtoModel(UserProfile entity);

    UserProfile toEntity(UserProfileDTO dto);
}
