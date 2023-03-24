package com.example.cslink.user.cosmetologist.controller.mappers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cslink.user.cosmetologist.controller.mappers.CosmetologistMapper;
import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDTO;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.user.controller.mappers.UserProfileMapper;
import com.example.cslink.user.controller.service.UserProfileService;

@Component
public class CosmetologistMapperImpl implements CosmetologistMapper {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public CosmetologistDTO toCosmetologistDTO(Cosmetologist cosmetologist) {
        CosmetologistDTO dto = new CosmetologistDTO();
        dto.setId(cosmetologist.getId());
        dto.setUserProfileId(cosmetologist.getUserProfile().getId());
        //        dto.setSpecialty(cosmetologist.getSpecialty());
        //        dto.setYearsOfExperience(cosmetologist.getYearsOfExperience());
        List<Long> procedureIds = cosmetologist.getProcedures().stream()
                .map(Procedure::getId)
                .collect(Collectors.toList());
        dto.setProcedureIds(procedureIds);
        //dto.setAvailability(cosmetologist.getAvailability());
        return dto;
    }

    @Override
    public Cosmetologist toCosmetologistEntity(CosmetologistDTO dto) {
        Cosmetologist cosmetologist = new Cosmetologist();
        cosmetologist.setId(cosmetologist.getId());
        cosmetologist.setUserProfile(userProfileMapper.toEntity(userProfileService.getUserProfileById(dto.getUserProfileId())));
        //        cosmetologist.setSpecialty(dto.getSpecialty());
        //        cosmetologist.setYearsOfExperience(dto.getYearsOfExperience());
        List<Procedure> procedures = new ArrayList<>();
        if (dto.getProcedureIds() != null) {
            for (Long procedureId : dto.getProcedureIds()) {
                Procedure procedure = new Procedure();
                procedure.setId(procedureId);
                procedures.add(procedure);
            }
        }
        cosmetologist.setProcedures(procedures);
        // cosmetologist.setAvailability(dto.getAvailability());
        return cosmetologist;
    }
}
