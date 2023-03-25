package com.example.cslink.management.reservation.controller.service.impl;

import com.example.cslink.exceptions.CustomResourceNotFoundException;
import com.example.cslink.management.reservation.controller.mappers.ReservationAssembler;
import com.example.cslink.management.reservation.controller.mappers.ReservationMapper;
import com.example.cslink.management.reservation.controller.service.ReservationService;
import com.example.cslink.management.reservation.model.dao.ReservationDao;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;
import com.example.cslink.management.reservation.model.datatypes.valueobject.AppointmentTime;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.schedule.model.entity.Availability;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.procedure.model.dao.ProcedureDao;
import com.example.cslink.user.model.dataccess.dao.CosmetologistDao;
import com.example.cslink.user.model.entity.Cosmetologist;
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
    private ProcedureDao procedureRepository;

    @Autowired
    private CosmetologistDao cosmetologistRepository;


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
        // todo make custom exceptions
        Procedure procedure=procedureRepository.findById(reservation.getProcedure().getId())
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        boolean cosmetologistHasProcedure=cosmetologistRepository
                .existsByIdAndProceduresId(reservation.getCosmetologist().getId(), procedure.getId());
        if (!cosmetologistHasProcedure) {
            throw new RuntimeException("Cosmetologist does not offer this procedure");
        }

        Cosmetologist cosmetologist=reservation.getCosmetologist();
        AppointmentTime appointmentTime=reservation.getAppointmentTime();
        List<Availability> availabilities=cosmetologist.getAvailability();
        boolean isAvailable=availabilities.stream()
                .anyMatch(availability -> availability.getWorkingHours().getDayOfWeek() == appointmentTime.getStartTime().getDayOfWeek()
                        && availability.getWorkingHours().getStartTime().compareTo(appointmentTime.getStartTime().toLocalTime()) <= 0
                        && availability.getWorkingHours().getEndTime().compareTo(appointmentTime.getEndTime().toLocalTime()) >= 0);
        if (!isAvailable) {
            throw new RuntimeException("Cosmetologist is not available at this time");
        }

        Reservation savedReservation=reservationRepository.save(reservation);
        return reservationAssembler.toReservationDTO(savedReservation);
    }

    @Override
    public ReservationDto updateReservation(Long id, Reservation reservation) {
        return null;
    }


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
