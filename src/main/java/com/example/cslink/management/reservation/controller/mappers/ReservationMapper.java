package com.example.cslink.management.reservation.controller.mappers;

import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public interface ReservationMapper {
    ReservationDto toDTO(Reservation entity);
}
