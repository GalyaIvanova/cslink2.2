package com.example.cslink.management.reservation.controller.mappers.impl;

import com.example.cslink.management.reservation.controller.mappers.ReservationMapper;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapperImpl implements ReservationMapper {
    @Override
    public ReservationDto toDTO(Reservation entity) {
        ReservationDto reservationDTO=new ReservationDto();
        reservationDTO.setId(entity.getId());
//        reservationDTO.setAppointmentTime(entity.getAppointmentTime());
        reservationDTO.setClientId(entity.getClient().getId());
//        reservationDTO.setProcedure(entity.getProcedure());

        reservationDTO.setCosmetologistId(entity.getCosmetologist().getId());
        return reservationDTO;
    }
}
