package com.example.cslink.management.reservation.controller.mappers;

import com.example.cslink.user.controller.service.CosmetologistService;
import com.example.cslink.user.controller.service.CustomerService;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationAssembler {

    @Autowired
    private CustomerService clientService;
    @Autowired
    private CosmetologistService cosmetologisttService;

    public ReservationDto toReservationDTO(Reservation reservation) {
        ReservationDto reservationDTO=new ReservationDto();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setAppointmentTime(reservation.getAppointmentTime());
        reservationDTO.setClientId(reservation.getClient().getId());
        reservationDTO.setCosmetologistId(reservation.getCosmetologist().getId());
        reservationDTO.setProcedure(reservation.getProcedure());
        return reservationDTO;
    }

    public Reservation toReservationEntity(ReservationDto reservationDTO) {
        Reservation reservation=new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setAppointmentTime(reservationDTO.getAppointmentTime());
        reservation.setClient(clientService.getClientById(reservationDTO.getClientId()));
        reservation.setCosmetologist(cosmetologisttService.getCosmetologistById(reservationDTO.getCosmetologistId()));
        reservation.setProcedure(reservationDTO.getProcedure());
        return reservation;
    }


    public List<ReservationDto> toReservationDTOList(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::toReservationDTO)
                .collect(Collectors.toList());
    }

    public List<Reservation> toReservationEntityList(List<ReservationDto> reservationDTOs) {
        return reservationDTOs.stream()
                .map(this::toReservationEntity)
                .collect(Collectors.toList());
    }
}

