package com.example.cslink.management.reservation.controller.service;

import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.procedure.model.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO getReservationById(Long id);

    ReservationDTO createReservation(Reservation reservation);

    ReservationDTO updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

    List<ReservationDTO> getReservationsByClient(Long clientId);

    List<ReservationDTO> getReservationsByCosmetologist(Long cosmetologistId);

    List<ReservationDTO> getAllReservations();
}
