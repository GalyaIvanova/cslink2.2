package com.example.cslink.user.cosmetologist.controller;

import com.example.cslink.procedure.model.dto.ProcedureDto;
import com.example.cslink.user.cosmetologist.controller.mappers.CosmetologistMapper;
import com.example.cslink.user.cosmetologist.controller.mappers.impl.CosmetologistMapperImpl;
import com.example.cslink.user.cosmetologist.controller.service.CosmetologistService;
import com.example.cslink.user.cosmetologist.model.dataccess.dao.CosmetologistDao;
import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDto;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cosmetologist")
public class CosmetologistController {

    @Autowired
    private CosmetologistDao cosmetologistRepository;
    @Autowired
    private CosmetologistService cosmetologistService;
    @Autowired
    private CosmetologistMapper cosmetologistMapper;

    @GetMapping("")
    public ResponseEntity<List<CosmetologistDto>> getAllCosmetologists() {
        List<CosmetologistDto> cosmetologists=cosmetologistService.getAllCosmetologists();
        return ResponseEntity.ok(cosmetologists);
    }

    @GetMapping("/{cosmetologistId}")
    public ResponseEntity<CosmetologistDto> getCosmetologistById(@PathVariable Long cosmetologistId) {
        Cosmetologist cosmetologist=cosmetologistService.getCosmetologistById(cosmetologistId);
        CosmetologistDto cosmetologistDto=cosmetologistMapper.toCosmetologistDTO(cosmetologist);
        return ResponseEntity.ok(cosmetologistDto);
    }

    @PostMapping("/create")
    public ResponseEntity<CosmetologistDto> createCosmetologist(@RequestBody CosmetologistDto cosmetologistDTO) {
        CosmetologistDto createdCosmetologist=cosmetologistService.createCosmetologist(cosmetologistDTO);
        return ResponseEntity.created(URI.create("/api/cosmetologists/" + createdCosmetologist.getId()))
                .body(createdCosmetologist);
    }

    @PutMapping("/update/{cosmetologistId}")
    public ResponseEntity<CosmetologistDto> updateCosmetologist(@PathVariable Long cosmetologistId, @RequestBody CosmetologistDto cosmetologistDTO) {
        Cosmetologist cosmetologist = cosmetologistService.getCosmetologistById(cosmetologistId);
        if (cosmetologist == null) {
            return ResponseEntity.notFound().build();
        }
        CosmetologistDto updatedCosmetologist=cosmetologistService.updateCosmetologist(cosmetologistId, cosmetologistDTO);
        return ResponseEntity.ok(updatedCosmetologist);
    }

    @DeleteMapping("/{cosmetologistId}")
    public ResponseEntity<Void> deleteCosmetologist(@PathVariable Long cosmetologistId) {
        cosmetologistService.deleteCosmetologist(cosmetologistId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cosmetologistId}/procedures")
    public ResponseEntity<CosmetologistDto> addProcedureToCosmetologist(@PathVariable Long cosmetologistId, @RequestBody ProcedureDto procedureDTO) {
        Cosmetologist cosmetologist=cosmetologistService.addProcedureToCosmetologist(cosmetologistId, procedureDTO.getId());
        CosmetologistDto updatedCosmetologist=cosmetologistMapper.toCosmetologistDTO(cosmetologist);
        return ResponseEntity.ok(updatedCosmetologist);
    }

    @DeleteMapping("/{cosmetologistId}/procedures/{procedureId}")
    public ResponseEntity<CosmetologistDto> removeProcedureFromCosmetologist(@PathVariable Long cosmetologistId, @PathVariable Long procedureId) {
        Cosmetologist cosmetologist=cosmetologistService.removeProcedureFromCosmetologist(cosmetologistId, procedureId);
        CosmetologistDto updatedCosmetologist=cosmetologistMapper.toCosmetologistDTO(cosmetologist);
        return ResponseEntity.ok(updatedCosmetologist);
    }
}
