package com.example.cslink.management.reservation.model.datatypes.dto;

import com.example.cslink.management.reservation.model.datatypes.valueobject.AppointmentTime;
import com.example.cslink.procedure.model.Procedure;

import java.util.Objects;

public class ReservationDto {
    private Long id;
    private Long clientId;
    private Long cosmetologistId;
    private Long procedureId;
    private AppointmentTime appointmentTime;
    private Procedure procedure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId=clientId;
    }

    public Long getCosmetologistId() {
        return cosmetologistId;
    }

    public void setCosmetologistId(Long cosmetologistId) {
        this.cosmetologistId=cosmetologistId;
    }

    public Long getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Long procedureId) {
        this.procedureId=procedureId;
    }

    public AppointmentTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(AppointmentTime appointmentTime) {
        this.appointmentTime=appointmentTime;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure=procedure;
    }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ReservationDto that=(ReservationDto) o;
                return Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && Objects.equals(cosmetologistId, that.cosmetologistId) && Objects.equals(procedureId, that.procedureId) && Objects.equals(appointmentTime, that.appointmentTime) && Objects.equals(procedure, that.procedure);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, clientId, cosmetologistId, procedureId, appointmentTime, procedure);
        }

        @Override
        public String toString() {
                return "ReservationDto{" +
                        "id=" + id +
                        ", clientId=" + clientId +
                        ", cosmetologistId=" + cosmetologistId +
                        ", procedureId=" + procedureId +
                        ", appointmentTime=" + appointmentTime +
                        ", procedure=" + procedure +
                        '}';
        }
}
