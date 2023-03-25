package com.example.cslink.management.reservation.model.datatypes.valueobject;

import jakarta.persistence.Embeddable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AppointmentTime {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public AppointmentTime() {
    }

    public AppointmentTime(LocalDateTime start, LocalDateTime end) {
        //LocalDateTime start = LocalDateTime.of(2022, 3, 1, 9, 0);
        //LocalDateTime end = LocalDateTime.of(2022, 3, 1, 10, 30);
        //AppointmentTime appointmentTime = new AppointmentTime(start, end);
        this.startTime=start;
        this.endTime=end;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime=startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime=endTime;
    }

    public Duration timeDuration() {
        return Duration.between(startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentTime that=(AppointmentTime) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }
}

