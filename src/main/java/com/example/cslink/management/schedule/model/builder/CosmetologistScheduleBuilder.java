package com.example.cslink.management.schedule.model.builder;

import com.example.cslink.management.schedule.model.valueobject.CosmetologistSchedule;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
public class CosmetologistScheduleBuilder extends CosmetologistSchedule {
    private String cosmetologistName;
    private LocalDateTime appointmentTime;
    private String procedureName;
    private Duration duration;
    private BigDecimal price;
    private String customerName;
    private String customerPhone;

    public CosmetologistScheduleBuilder withCosmetologistName(String cosmetologistName) {
        this.cosmetologistName = cosmetologistName;
        return this;
    }

    public CosmetologistScheduleBuilder withAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
        return this;
    }

    public CosmetologistScheduleBuilder withProcedureName(String procedureName) {
        this.procedureName = procedureName;
        return this;
    }

    public CosmetologistScheduleBuilder withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public CosmetologistScheduleBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CosmetologistScheduleBuilder withCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public CosmetologistScheduleBuilder withCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
        return this;
    }

    public CosmetologistSchedule build() {
        return new CosmetologistSchedule(cosmetologistName, appointmentTime, procedureName, duration, price, customerName, customerPhone);
    }
}
