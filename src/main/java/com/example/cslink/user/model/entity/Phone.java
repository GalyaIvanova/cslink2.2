package com.example.cslink.user.model.entity;

import jakarta.persistence.Embeddable;
import org.springframework.lang.NonNull;

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
}
