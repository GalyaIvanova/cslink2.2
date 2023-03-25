package com.example.cslink.management.reservation.model.datatypes.valueobject;

import jakarta.persistence.Embeddable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class AppointmentTime {
    private LocalDateTime start;
    private LocalDateTime end;

    public AppointmentTime() {
    }

    public AppointmentTime(LocalDateTime start, LocalDateTime end) {
        //LocalDateTime start = LocalDateTime.of(2022, 3, 1, 9, 0);
        //LocalDateTime end = LocalDateTime.of(2022, 3, 1, 10, 30);
        //AppointmentTime appointmentTime = new AppointmentTime(start, end);
        this.start=start;
        this.end=end;
    }


    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start=start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end=end;
    }

    public Duration timeDuration() {
        return Duration.between(start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentTime that=(AppointmentTime) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}

