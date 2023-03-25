package com.example.cslink.management.reservation.model.entity;

import com.example.cslink.user.model.entity.Cosmetologist;
import com.example.cslink.user.model.entity.Customer;
import com.example.cslink.management.reservation.model.datatypes.valueobject.AppointmentTime;
import com.example.cslink.procedure.model.Procedure;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cosmetologist_id")
    private Cosmetologist cosmetologist;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Customer client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedure_id", nullable = false)
    private Procedure procedure;

    @Embedded
    private AppointmentTime appointmentTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cosmetologist getCosmetologist() {
        return cosmetologist;
    }

    public void setCosmetologist(Cosmetologist cosmetologist) {
        this.cosmetologist = cosmetologist;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public AppointmentTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(AppointmentTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that=(Reservation) o;
        return Objects.equals(id, that.id)
                && Objects.equals(cosmetologist, that.cosmetologist)
                && Objects.equals(client, that.client)
                && Objects.equals(procedure, that.procedure)
                && Objects.equals(appointmentTime, that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cosmetologist, client, procedure, appointmentTime);
    }
}
