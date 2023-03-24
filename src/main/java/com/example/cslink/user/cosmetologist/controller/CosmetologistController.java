package com.example.cslink.user.cosmetologist.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.cslink.procedure.model.dto.ProcedureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.cslink._useless.dto.ProcedureDTO;
import com.example.cslink.user.cosmetologist.controller.mappers.impl.CosmetologistMapperImpl;
import com.example.cslink.user.cosmetologist.controller.service.CosmetologistService;
import com.example.cslink.user.cosmetologist.model.dataccess.dao.CosmetologistDao;
import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDTO;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;

@RestController
@RequestMapping("/cosmetologists")
public class CosmetologistController {

    @Autowired
    private CosmetologistDao cosmetologistRepository;
    @Autowired
    private CosmetologistService cosmetologistService;
    @Autowired
    private CosmetologistMapperImpl cosmetologistAssembler;

//    @GetMapping
//    public List<Cosmetologist> getCosmetologists() {
//        return cosmetologistRepository.findAll();
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Cosmetologist> getCosmetologistById(@PathVariable(value = "id") Long id) {
//        Optional<Cosmetologist> cosmetologist = cosmetologistRepository.findById(id);
//        return cosmetologist.map(value -> ResponseEntity.ok().body(value))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @PostMapping
//    public Cosmetologist createCosmetologist(@RequestBody Cosmetologist cosmetologist) {
//        return cosmetologistRepository.save(cosmetologist);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Cosmetologist> updateCosmetologist(@PathVariable(value = "id") Long id,
                                                             @RequestBody Cosmetologist cosmetologistDetails) {
        Optional<Cosmetologist> optionalCosmetologist = cosmetologistRepository.findById(id);
        if (optionalCosmetologist.isPresent()) {
            Cosmetologist cosmetologist = optionalCosmetologist.get();
            //cosmetologist.setName(cosmetologistDetails.getName());
            Cosmetologist updatedCosmetologist = cosmetologistRepository.save(cosmetologist);
            return ResponseEntity.ok(updatedCosmetologist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/{cosmetologistId}/procedures/{procedureId}")
//    public ResponseEntity<Void> deleteCosmetologist(@PathVariable(value = "id") Long id) {
//        Optional<Cosmetologist> cosmetologist = cosmetologistRepository.findById(id);
//        if (cosmetologist.isPresent()) {
//            cosmetologistRepository.delete(cosmetologist.get());
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//    /


    @GetMapping("")
    public ResponseEntity<List<CosmetologistDTO>> getAllCosmetologists() {
        List<CosmetologistDTO> cosmetologists = cosmetologistService.getAllCosmetologists();
        return ResponseEntity.ok(cosmetologists);
    }

    @GetMapping("/{cosmetologistId}")
    public ResponseEntity<CosmetologistDTO> getCosmetologistById(@PathVariable Long cosmetologistId) {
        Cosmetologist cosmetologist=cosmetologistService.getCosmetologistById(cosmetologistId);
        CosmetologistDTO cosmetologistDto=cosmetologistAssembler.toCosmetologistDTO(cosmetologist);
        return ResponseEntity.ok(cosmetologistDto);
    }

    @PostMapping("")
    public ResponseEntity<CosmetologistDTO> createCosmetologist(@RequestBody CosmetologistDTO cosmetologistDTO) {
        CosmetologistDTO createdCosmetologist = cosmetologistService.createCosmetologist(cosmetologistDTO);
        return ResponseEntity.created(URI.create("/api/cosmetologists/" + createdCosmetologist.getId()))
                .body(createdCosmetologist);
    }

    @PutMapping("/{cosmetologistId}")
    public ResponseEntity<CosmetologistDTO> updateCosmetologist(@PathVariable Long cosmetologistId, @RequestBody CosmetologistDTO cosmetologistDTO) {
        CosmetologistDTO updatedCosmetologist = cosmetologistService.updateCosmetologist(cosmetologistId, cosmetologistDTO);
        return ResponseEntity.ok(updatedCosmetologist);
    }

    @DeleteMapping("/{cosmetologistId}")
    public ResponseEntity<Void> deleteCosmetologist(@PathVariable Long cosmetologistId) {
        cosmetologistService.deleteCosmetologist(cosmetologistId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cosmetologistId}/procedures")
    public ResponseEntity<CosmetologistDTO> addProcedureToCosmetologist(@PathVariable Long cosmetologistId, @RequestBody ProcedureDTO procedureDTO) {
        Cosmetologist cosmetologist=cosmetologistService.addProcedureToCosmetologist(cosmetologistId, procedureDTO.getId());
        CosmetologistDTO updatedCosmetologist =cosmetologistAssembler.toCosmetologistDTO(cosmetologist);
        return ResponseEntity.ok(updatedCosmetologist);
    }

    @DeleteMapping("/{cosmetologistId}/procedures/{procedureId}")
    public ResponseEntity<CosmetologistDTO> removeProcedureFromCosmetologist(@PathVariable Long cosmetologistId, @PathVariable Long procedureId) {
        Cosmetologist cosmetologist=cosmetologistService.removeProcedureFromCosmetologist(cosmetologistId, procedureId);
        CosmetologistDTO updatedCosmetologist =cosmetologistAssembler.toCosmetologistDTO(cosmetologist) ;
        return ResponseEntity.ok(updatedCosmetologist);
    }
}
