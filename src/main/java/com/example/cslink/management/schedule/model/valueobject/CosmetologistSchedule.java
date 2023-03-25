package com.example.cslink.management.schedule.model.valueobject;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public CosmetologistSchedule() {
    }

    public String getCosmetologistName() {
        return cosmetologistName;
    }

    public void setCosmetologistName(String cosmetologistName) {
        this.cosmetologistName=cosmetologistName;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime=appointmentTime;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName=procedureName;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration=duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price=price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName=customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone=customerPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CosmetologistSchedule that=(CosmetologistSchedule) o;
        return Objects.equals(cosmetologistName, that.cosmetologistName)
                && Objects.equals(appointmentTime, that.appointmentTime)
                && Objects.equals(procedureName, that.procedureName)
                && Objects.equals(duration, that.duration)
                && Objects.equals(price, that.price)
                && Objects.equals(customerName, that.customerName)
                && Objects.equals(customerPhone, that.customerPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cosmetologistName, appointmentTime, procedureName, duration, price, customerName, customerPhone);
    }

    @Override
    public String toString() {
        return "CosmetologistSchedule{" +
                "cosmetologistName='" + cosmetologistName + '\'' +
                ", appointmentTime=" + appointmentTime +
                ", procedureName='" + procedureName + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                '}';
    }
}
