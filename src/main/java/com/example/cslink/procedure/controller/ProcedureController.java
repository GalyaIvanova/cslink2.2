package com.example.cslink.procedure.controller;

import com.example.cslink.procedure.controller.service.ProcedureService;
import com.example.cslink.procedure.model.dto.ProcedureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @Autowired
    private ProcedureAssembler procedureAssembler;


    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDto> getProcedureById(@PathVariable("id") Long id) {
        ProcedureDto procedure=procedureService.getProcedureById(id);
        return ResponseEntity.ok(procedure);
    }

    @GetMapping
    public ResponseEntity<List<ProcedureDto>> getAllProcedures() {
        List<ProcedureDto> procedures=procedureService.getAllProcedures();
//        List<ProcedureDTO> procedureDTOs = procedures.stream()
//                .map(procedureAssembler::toProcedureDTO)
//                .collect(Collectors.toList());
        return ResponseEntity.ok(procedures);
    }

    @PostMapping("/create")
    public ResponseEntity<ProcedureDto> createProcedure(@RequestBody ProcedureDto procedureDTO) {
        ProcedureDto createdProcedureDTO=procedureService.createProcedure(procedureDTO);
        return ResponseEntity.ok(createdProcedureDTO);
//        (createdProcedureDTO.getRequiredLink(IanaLinkRelations.SELF).toUri())
//                .body(createdProcedureDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedureDto> updateProcedure(@PathVariable("id") Long id, @RequestBody ProcedureDto procedureDTO) {
        ProcedureDto updatedProcedureDTO=procedureService.updateProcedure(id, procedureDTO);
        return ResponseEntity.ok(updatedProcedureDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedure(@PathVariable("id") Long id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.noContent().build();
    }
}
