package com.example.cslink.management.schedule.model.ds;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class CosmetologistSchedule {
    private String cosmetologistName;
    private LocalDateTime appointmentTime;
    private String procedureName;
    private Duration duration;
    private BigDecimal price;
    private String customerName;
    private String customerPhone;

    public CosmetologistSchedule(String cosmetologistName, LocalDateTime appointmentTime, String procedureName,
                                 Duration duration, BigDecimal price, String customerName, String customerPhone) {
        this.cosmetologistName=cosmetologistName;
        this.appointmentTime=appointmentTime;
        this.procedureName=procedureName;
        this.duration=duration;
        this.price=price;
        this.customerName=customerName;
        this.customerPhone=customerPhone;
    }

    public static class builder{
        private String cosmetologistName;
        private LocalDateTime appointmentTime;
        private String procedureName;
        private Duration duration;
        private BigDecimal price;
        private String customerName;
        private String customerPhone;

        public builder withCosmetologistName(String cosmetologistName) {
            this.cosmetologistName=cosmetologistName;
            return this;
        }

        public builder withAppointmentTime(LocalDateTime appointmentTime) {
            this.appointmentTime=appointmentTime;
            return this;
        }

        public builder withProcedureName(String procedureName) {
            this.procedureName=procedureName;
            return this;
        }

        public builder withDuration(Duration duration) {
            this.duration=duration;
            return this;
        }

        public builder withPrice(BigDecimal price) {
            this.price=price;
            return this;
        }

        public builder withCustomerName(String customerName) {
            this.customerName=customerName;
            return this;
        }

        public builder withCustomerPhone(String customerPhone) {
            this.customerPhone=customerPhone;
            return this;
        }

        public CosmetologistSchedule build() {
            return new CosmetologistSchedule(cosmetologistName, appointmentTime, procedureName, duration, price, customerName, customerPhone);
        }
    }
}
