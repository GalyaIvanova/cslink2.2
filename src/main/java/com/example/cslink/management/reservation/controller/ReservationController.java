package com.example.cslink.management.reservation.controller;

import com.example.cslink.management.reservation.controller.mappers.ReservationAssembler;
import com.example.cslink.management.reservation.controller.service.ReservationService;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.procedure.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

//    @Autowired
//    private CustomerService clientService;
//
//    @Autowired
//    private CosmetologistService cosmetologistService;

//    @Autowired
//    private ClientAssembler clientAssembler;

//    @Autowired
//    private CosmetologistAssembler cosmetologistAssembler;

    @Autowired
    private ReservationAssembler reservationAssembler;


//    @GetMapping("/{id}")
//    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id) {
//        ReservationDTO reservationDTO = reservationService.getReservationById(id);
//        return ResponseEntity.ok(reservationDTO);
//    }

//    @PostMapping("")
//    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
//
//        ReservationDTO createdReservationDTO = reservationService.createReservation(reservationAssembler.toReservationEntity(reservationDTO));
//        return ResponseEntity.created(URI.create("/reservations/" + createdReservationDTO.getId())).body(createdReservationDTO);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable("id") Long id, @RequestBody ReservationDTO reservationDTO) {
        Reservation reservation=reservationAssembler.toReservationEntity(reservationDTO);
        reservationDTO=reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(reservationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-client/{clientId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByClient(@PathVariable("clientId") Long clientId) {
        List<ReservationDTO> reservations=reservationService.getReservationsByClient(clientId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/by-cosmetologist/{cosmetologistId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByCosmetologist(@PathVariable("cosmetologistId") Long cosmetologistId) {
        List<ReservationDTO> reservations=reservationService.getReservationsByCosmetologist(cosmetologistId);
        return ResponseEntity.ok(reservations);
    }

//    @PostMapping("/with-procedure")
//    public ResponseEntity<ReservationDTO> createReservationWithProcedure(@RequestBody ReservationDTO reservationDTO) {
//        // check if client and cosmetologist exist
//        ClientDTO clientDTO =  clientAssembler.toDTO(clientService.getClientById(reservationDTO.getClientId()));
//        CosmetologistDTO cosmetologistDTO = cosmetologistService.getCosmetologistById(reservationDTO.getCosmetologistId());
//
//        if (clientDTO == null || cosmetologistDTO == null) {
//            throw new CustomResourceNotFoundException("Client or cosmetologist not found");
//        }
//
//        // create reservation and assign procedure
//        ReservationDTO reservationDTO = new ReservationDTO();
//        reservationDTO.setClient(clientDTO);
//        reservationDTO.setCosmetologist(cosmetologistDTO);
//        reservationDTO.setStartTime(reservationWithProcedureDTO.getStartTime());
//        reservationDTO.setEndTime(reservationWithProcedureDTO.getEndTime());
//        reservationDTO.setProcedure(reservationWithProcedureDTO.getProcedure());
//
//        ReservationDTO createdReservationDTO = reservationService.createReservation(reservationDTO);
//
//        return ResponseEntity.created(URI.create("/reservations/" + createdReservationDTO.getId())).body(createdReservationDTO);
//    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservationDTO=reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationDTO);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation=reservationAssembler.toReservationEntity(reservationDTO);
        reservationDTO=reservationService.createReservation(reservation);
        return ResponseEntity.ok(reservationDTO);
    }

//    @PutMapping("/reservations/{id}")
//    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
//        Reservation reservation = reservationAssembler.toReservationEntity(reservationDTO);
//        reservationDTO = reservationService.updateReservation(id, reservation);
//        return ResponseEntity.ok(reservationDTO);
//    }

//    @DeleteMapping("/reservations/{id}")
//    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
//        reservationService.deleteReservation(id);
//        return ResponseEntity.noContent().build();
//    }

}
