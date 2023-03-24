package com.example.cslink.management.reservation.controller.service.impl;

import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.management.reservation.controller.mappers.ReservationAssembler;
import com.example.cslink.management.reservation.controller.mappers.ReservationMapper;
import com.example.cslink.management.reservation.controller.service.ReservationService;
import com.example.cslink.management.reservation.model.dao.ReservationDao;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationAssembler reservationAssembler;
    @Autowired
    private ReservationDao reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Reservation", id));
        return reservationAssembler.toReservationDTO(reservation);

    }

    @Override
    public ReservationDto createReservation(Reservation reservation) {
        return reservationAssembler.toReservationDTO(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto updateReservation(Long id, Reservation reservation) {
        return null;
    }

//    @Override
//    public Reservation createReservation(Reservation reservation) {
//        return reservationRepository.save(reservation);
//    }

//    @Override
//    public Reservation updateReservation(Long id, Reservation reservation) {
//        Reservation existingReservation = reservationRepository.findById(id)
//                .orElseThrow(() -> new CustomResourceNotFoundException ("Reservation", id));
//
//        existingReservation.setClient(reservation.getClient());
//        existingReservation.setCosmetologist(reservation.getCosmetologist());
//        existingReservation.setAppointmentTime(reservation.getAppointmentTime());
//
//        return reservationRepository.save(existingReservation);
//    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Reservation", id));
        reservationRepository.delete(reservation);
    }

    @Override
    public List<ReservationDto> getReservationsByClient(Long clientId) {
        List<Reservation> reservations=reservationRepository.findByClientId(clientId);
        return reservationAssembler.toReservationDTOList(reservations);

    }

    @Override
    public List<ReservationDto> getReservationsByCosmetologist(Long cosmetologistId) {
        List<Reservation> reservations=reservationRepository.findByCosmetologistId(cosmetologistId);
        return reservationAssembler.toReservationDTOList(reservations);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        List<Reservation> reservations=reservationRepository.findAll();
        return reservationAssembler.toReservationDTOList(reservations);

    }
}
