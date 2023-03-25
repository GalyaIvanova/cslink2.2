package com.example.cslink.management.schedule.model.valueobject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingHours that=(WorkingHours) o;
        return dayOfWeek == that.dayOfWeek && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, date, startTime, endTime);
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
                "dayOfWeek=" + dayOfWeek +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
