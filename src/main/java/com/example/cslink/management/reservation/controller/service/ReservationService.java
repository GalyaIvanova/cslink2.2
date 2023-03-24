package com.example.cslink.management.reservation.controller.service;

import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    ReservationDto getReservationById(Long id);

    ReservationDto createReservation(Reservation reservation);

    ReservationDto updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

    List<ReservationDto> getReservationsByClient(Long clientId);

    List<ReservationDto> getReservationsByCosmetologist(Long cosmetologistId);

    List<ReservationDto> getAllReservations();
}
