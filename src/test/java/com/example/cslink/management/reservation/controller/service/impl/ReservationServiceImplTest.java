package com.example.cslink.management.reservation.controller.service.impl;

import com.example.cslink.management.reservation.controller.mappers.ReservationAssembler;
import com.example.cslink.management.reservation.model.dao.ReservationDao;
import com.example.cslink.management.reservation.model.datatypes.dto.ReservationDto;
import com.example.cslink.management.reservation.model.datatypes.valueobject.AppointmentTime;
import com.example.cslink.management.reservation.model.entity.Reservation;
import com.example.cslink.management.schedule.model.entity.Availability;
import com.example.cslink.management.schedule.model.valueobject.WorkingHours;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.procedure.model.dao.ProcedureDao;
import com.example.cslink.user.model.dataccess.dao.CosmetologistDao;
import com.example.cslink.user.model.dataccess.dao.CustomerDao;
import com.example.cslink.user.model.entity.Cosmetologist;
import com.example.cslink.user.model.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationDao reservationRepository;

    @Mock
    private ProcedureDao procedureRepository;

    @Mock
    private CosmetologistDao cosmetologistRepository;

    @Mock
    private CustomerDao customerRepository;

    @Mock
    private ReservationAssembler reservationAssembler;

    @InjectMocks
    private ReservationServiceImpl reservationService;


    @Test
    public void createReservation_shouldCreateReservation_ifProcedureAndAvailability_isValid() {
        Cosmetologist cosmetologistMock=Mockito.mock(Cosmetologist.class);
        Procedure procedure=Mockito.mock(Procedure.class);
        Customer customer=Mockito.mock(Customer.class);
        AppointmentTime appointmentTime=new AppointmentTime();
        appointmentTime.setStartTime(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), 12, 9));
        appointmentTime.setEndTime(LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(),
                LocalDate.now().getDayOfMonth(), 15, 30));
        Reservation reservation=new Reservation();
        reservation.setCosmetologist(cosmetologistMock);
        reservation.setClient(customer);
        reservation.setProcedure(procedure);
        reservation.setAppointmentTime(appointmentTime);
        List<Availability> availabilityList=new ArrayList<>();
        availabilityList.add(new Availability(new WorkingHours(LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0))));
        availabilityList.add(new Availability(new WorkingHours(LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0))));
        availabilityList.add(new Availability(new WorkingHours(LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(13, 0))));

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        when(reservationAssembler.toReservationDTO(any(Reservation.class))).thenReturn(new ReservationDto());
        when(cosmetologistRepository.existsByIdAndProceduresId(reservation.getCosmetologist().getId(), procedure.getId())).thenReturn(true);
        when(procedureRepository.findById(reservation.getProcedure().getId())).thenReturn(Optional.of(procedure));

        Mockito.when(cosmetologistMock.getAvailability()).thenReturn(availabilityList);

        ReservationDto reservationDto=reservationService.createReservation(reservation);

        verify(reservationRepository).save(any(Reservation.class));
        verify(reservationAssembler).toReservationDTO(any(Reservation.class));
        verify(procedureRepository).findById(anyLong());
        assertNotNull(reservationDto);
    }

    @Test(expected=RuntimeException.class)
    public void testCreateReservation_whenCosmetologistNotAvailable_shouldThrowException() {
        Cosmetologist cosmetologist=Mockito.mock(Cosmetologist.class);
        Procedure procedure=Mockito.mock(Procedure.class);
        Customer customer=Mockito.mock(Customer.class);
        AppointmentTime appointmentTime=new AppointmentTime();
        appointmentTime.setStartTime(LocalDateTime.of(2022, 3, 1, 9, 0));
        appointmentTime.setEndTime(LocalDateTime.of(2022, 3, 1, 10, 30));

        Reservation reservation=new Reservation();
        reservation.setCosmetologist(cosmetologist);
        reservation.setClient(customer);
        reservation.setProcedure(procedure);
        reservation.setAppointmentTime(appointmentTime);
        List<Availability> availabilityList=new ArrayList<>();
        availabilityList.add(new Availability(new WorkingHours(LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0))));
        availabilityList.add(new Availability(new WorkingHours(LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(17, 0))));

        //todo
//        when(procedureRepository.findById(anyLong())).thenReturn(Optional.of(procedure));
//        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
//        when(cosmetologistRepository.findById(anyLong())).thenReturn(Optional.of(cosmetologist));
//        when(reservationRepository.save(any(Reservation.class))).thenThrow(new RuntimeException("Cosmetologist is not available at this time"));
//        when(reservationAssembler.toReservationDTO(any(Reservation.class))).thenReturn(new ReservationDto());

        reservationService.createReservation(reservation);
    }

}