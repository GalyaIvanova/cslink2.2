package com.example.cslink.management.schedule.model.ds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public class WorkingHours {
    @Column(name = "dayOfWeek")
    private DayOfWeek dayOfWeek;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endTime")
    private LocalTime endTime;
    //private List<WorkingHours> availability;

    public WorkingHours() {
    }
    public WorkingHours(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {

        this.dayOfWeek=dayOfWeek;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public WorkingHours(LocalDate day, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek=day.getDayOfWeek();
        this.date =day;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime=startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime=endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek=dayOfWeek;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }


}
