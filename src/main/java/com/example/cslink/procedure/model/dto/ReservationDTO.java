package com.example.cslink.procedure.model.dto;

import com.example.cslink.management.reservation.model.datatypes.ds.AppointmentTime;
import com.example.cslink.procedure.model.Procedure;

public class ReservationDTO {
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
                this.id = id;
        }

        public Long getClientId() {
                return clientId;
        }

        public void setClientId(Long clientId) {
                this.clientId = clientId;
        }

        public Long getCosmetologistId() {
                return cosmetologistId;
        }

        public void setCosmetologistId(Long cosmetologistId) {
                this.cosmetologistId = cosmetologistId;
        }

        public Long getProcedureId() {
                return procedureId;
        }

        public void setProcedureId(Long procedureId) {
                this.procedureId = procedureId;
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
}
