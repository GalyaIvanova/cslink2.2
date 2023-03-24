package com.example.cslink.user.controller.mappers;

import com.example.cslink.user.model.dto.UserProfileDto;
import com.example.cslink.user.model.entity.UserProfile;
import jakarta.persistence.Converter;

@Converter
public interface UserProfileMapper {
    UserProfileDto toDtoModel(UserProfile entity);

    UserProfile toEntity(UserProfileDto dto);
}
