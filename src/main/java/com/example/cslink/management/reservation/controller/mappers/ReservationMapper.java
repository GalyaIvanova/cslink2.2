package com.example.cslink.management.reservation.controller.mappers;

import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.procedure.model.dto.ReservationDTO;
import org.springframework.stereotype.Component;

@Component
public interface ReservationMapper {
    ReservationDTO toDTO(Reservation entity);
}
