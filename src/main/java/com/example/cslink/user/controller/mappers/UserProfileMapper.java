package com.example.cslink.user.controller.mappers;

import com.example.cslink.user.model.dto.UserProfileDto;
import com.example.cslink.user.model.entity.UserProfile;

public interface UserProfileMapper {
    UserProfileDto toDtoModel(UserProfile entity);

    UserProfile toEntity(UserProfileDto dto);
}
