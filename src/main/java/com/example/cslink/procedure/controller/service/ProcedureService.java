package com.example.cslink.procedure.controller.service;

import com.example.cslink.procedure.model.dto.ProcedureDto;

import java.util.List;

public interface ProcedureService {

    ProcedureDto createProcedure(ProcedureDto procedureDTO);

    ProcedureDto getProcedureById(Long id);

    List<ProcedureDto> getAllProcedures();

    ProcedureDto updateProcedure(Long id, ProcedureDto procedureDTO);

    void deleteProcedure(Long id);

}
