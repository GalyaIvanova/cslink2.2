package com.example.cslink.user.model.entity;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userProfile_id", referencedColumnName="id")
    private UserProfile userProfile;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile=userProfile;
    }

    public Phone getPhone() {
        return this.userProfile.getPhone();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt=createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer=(Customer) o;
        return id.equals(customer.id)
                && userProfile.equals(customer.userProfile)
                && Objects.equals(createdAt, customer.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userProfile, createdAt);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", userProfile=" + userProfile +
                ", createdAt=" + createdAt +
                '}';
    }
}