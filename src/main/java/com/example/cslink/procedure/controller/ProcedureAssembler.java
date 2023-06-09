package com.example.cslink.procedure.controller;

import com.example.cslink.user.controller.service.CosmetologistService;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.procedure.model.dto.ProcedureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcedureAssembler {
    @Autowired
    private CosmetologistService cosmetologistService;

    public ProcedureDto toProcedureDTO(Procedure procedure) {
        ProcedureDto dto=new ProcedureDto();
        dto.setId(procedure.getId());
        dto.setCosmetologistId(procedure.getCosmetologist().getId());
        dto.setName(procedure.getName());
        dto.setDescription(procedure.getDescription());
        dto.setPrice(procedure.getPrice());
        return dto;
    }

    public Procedure toProcedureEntity(ProcedureDto dto) {
        Procedure procedure=new Procedure();
        procedure.setId(dto.getId());
        procedure.setCosmetologist(cosmetologistService.getCosmetologistById(dto.getCosmetologistId()));
        procedure.setName(dto.getName());
        procedure.setDescription(dto.getDescription());
        procedure.setPrice(dto.getPrice());
        return procedure;
    }
}


