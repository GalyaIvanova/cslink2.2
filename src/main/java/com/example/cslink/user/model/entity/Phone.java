package com.example.cslink.user.model.entity;

import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Embeddable
public class Phone {

    private String countryCode;
    @NonNull
    private String number;

    public Phone(String countryCode, @NonNull String number) {
        this.countryCode=countryCode;
        this.number=number;
    }

    public Phone() {
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Phone Number: ");
        if (countryCode != null && !countryCode.isEmpty()) {
            sb.append("+").append(countryCode).append("-");
        }
        sb.append(number);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone=(Phone) o;
        return Objects.equals(countryCode, phone.countryCode) && number.equals(phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }
}
