package com.example.cslink.procedure.controller;

import com.example.cslink.exceptions.CustomBadRequestException;
import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.management.reservation.controller.service.ReservationService;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.procedure.model.ProcedureRepository;
import com.example.cslink.procedure.model.dto.ProcedureDTO;
import com.example.cslink.procedure.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private ProcedureAssembler procedureAssembler;
    @Autowired
    private ReservationService reservationService;


    @Override
    public List<ProcedureDTO> getAllProcedures() {
        List<Procedure> procedures=procedureRepository.findAll();
        return procedures.stream().map(procedureAssembler::toProcedureDTO).collect(Collectors.toList());
    }

    @Override
    public ProcedureDTO getProcedureById(Long id) {
        Procedure procedure=procedureRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure", id));
        return procedureAssembler.toProcedureDTO(procedure);
    }

    @Override
    public ProcedureDTO createProcedure(ProcedureDTO procedureDTO) {
        if (procedureDTO.getName() == null || procedureDTO.getName().isEmpty()) {
            throw new CustomBadRequestException("Procedure name is required");
        }

        Procedure procedure=procedureAssembler.toProcedureEntity(procedureDTO);
        Procedure savedProcedure=procedureRepository.save(procedure);
        return procedureAssembler.toProcedureDTO(savedProcedure);
    }

    @Override
    public ProcedureDTO updateProcedure(Long id, ProcedureDTO procedureDTO) {
        Procedure existingProcedure=procedureRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure", id));

        if (procedureDTO.getName() == null || procedureDTO.getName().isEmpty()) {
            throw new CustomBadRequestException("Procedure name is required");
        }

        existingProcedure.setName(procedureDTO.getName());
        existingProcedure.setPrice(procedureDTO.getPrice());
        Procedure savedProcedure=procedureRepository.save(existingProcedure);
        return procedureAssembler.toProcedureDTO(savedProcedure);
    }

    @Override
    public void deleteProcedure(Long id) {
        Procedure procedure=procedureRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure", id));

        List<ReservationDTO> allReservations=reservationService.getAllReservations();

        if (isReserved(procedure.getId(), allReservations)) {
            throw new CustomBadRequestException("Procedure has appointments and cannot be deleted");
        }
        procedureRepository.delete(procedure);
    }

    private boolean isReserved(Long procedureId, List<ReservationDTO> allReservations) {
        return allReservations.removeIf(r -> Objects.equals(r.getProcedureId(), procedureId));
    }

//    @Override
//    public ProcedureDTO addCosmetologistToProcedure(Long procedureId, Long cosmetologistId) {
//        Procedure procedure = procedureRepository.findById(procedureId)
//                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure",  procedureId));
//        Cosmetologist cosmetologist = cosmetologistRepository.findById(cosmetologistId)
//                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));
//        if (procedure.getCosmetologists().contains(cosmetologist)) {
//            throw new CustomBadRequestException("Cosmetologist is already associated with this procedure");
//        }
//        procedure.addCosmetologist(cosmetologist);
//        Procedure savedProcedure = procedureRepository.save(procedure);
//        return procedureAssembler.toProcedureDTO(savedProcedure);
//    }

//    @Override
//    public void removeCosmetologistFromProcedure(Long procedureId, Long cosmetologistId) {
//        Procedure procedure = procedureRepository.findById(procedureId)
//                .orElseThrow(() -> new CustomResourceNotFoundException("Procedure",  procedureId));
//
//        Cosmetologist cosmetologist = cosmetologistRepository.findById(cosmetologistId)
//                .orElseThrow(() -> new CustomResourceNotFoundException("Cosmetologist", cosmetologistId));
//
//        if (!procedure.getCosmetologists().contains(cosmetologist)) {
//            throw new CustomBadRequestException("The cosmetologist is not assigned to this procedure.");
//        }
//
//        procedure.getCosmetologists().remove(cosmetologist);
//        procedureRepository.save(procedure);
//    }

    ///

//    @Override
//    public ProcedureDTO createProcedure(ProcedureDTO procedureDTO) {
//        // TODO: Implement this method
//        return null;
//    }
//
//    @Override
//    public ProcedureDTO getProcedureById(Long id) {
//        // TODO: Implement this method
//        return null;
//    }
//
//    @Override
//    public List<ProcedureDTO> getAllProcedures() {
//        // TODO: Implement this method
//        return null;
//    }
//
//    @Override
//    public void updateProcedure(Long id, ProcedureDTO procedureDTO) {
//        // TODO: Implement this method
//    }
//
//    @Override
//    public void deleteProcedure(Long id) {
//        // TODO: Implement this method
//    }
}

