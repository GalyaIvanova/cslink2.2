package com.example.cslink.management.reservation.model.datatypes.ds;

import jakarta.persistence.Embeddable;

import java.time.Duration;
import java.time.LocalDateTime;

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
        this.start = start;
        this.end = end;
    }


    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }
}

