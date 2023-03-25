package com.example.cslink.user.controller.mappers.impl;

import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.user.controller.mappers.UserProfileMapper;
import com.example.cslink.user.controller.service.UserProfileService;
import com.example.cslink.user.controller.mappers.CosmetologistMapper;
import com.example.cslink.user.model.dto.CosmetologistDto;
import com.example.cslink.user.model.entity.Cosmetologist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CosmetologistMapperImpl implements CosmetologistMapper {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public CosmetologistDto toCosmetologistDTO(Cosmetologist cosmetologist) {
        CosmetologistDto dto=new CosmetologistDto();
        dto.setId(cosmetologist.getId());
        dto.setUserProfileId(cosmetologist.getUserProfile().getId());
        dto.setSpecialty(cosmetologist.getSpecialty());
        dto.setYearsOfExperience(cosmetologist.getYearsOfExperience());
        List<Long> procedureIds=cosmetologist.getProcedures().stream()
                .map(Procedure::getId)
                .collect(Collectors.toList());
        dto.setProcedureIds(procedureIds);
        dto.setAvailability(cosmetologist.getAvailability());
        return dto;
    }

    @Override
    public Cosmetologist toCosmetologistEntity(CosmetologistDto dto) {
        Cosmetologist cosmetologist=new Cosmetologist();
        cosmetologist.setId(cosmetologist.getId());
        cosmetologist.setUserProfile(userProfileMapper.toEntity(userProfileService.getUserProfileById(dto.getUserProfileId())));
        cosmetologist.setSpecialty(dto.getSpecialty());
        cosmetologist.setYearsOfExperience(dto.getYearsOfExperience());
        List<Procedure> procedures=new ArrayList<>();
        if (dto.getProcedureIds() != null) {
            for (Long procedureId : dto.getProcedureIds()) {
                Procedure procedure=new Procedure();
                procedure.setId(procedureId);
                procedures.add(procedure);
            }
        }
        cosmetologist.setProcedures(procedures);
        cosmetologist.setAvailability(dto.getAvailability());
        return cosmetologist;
    }
}
