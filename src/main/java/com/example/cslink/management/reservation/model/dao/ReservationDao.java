package com.example.cslink.management.reservation.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cslink.user.model.entity.Cosmetologist;
import com.example.cslink.management.reservation.model.entity.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Long> {

    List<Reservation> findByCosmetologist(Cosmetologist cosmetologist);

    List<Reservation> findByClientId(Long clientId);

    List<Reservation> findByCosmetologistId(Long cosmetologistId);
}
