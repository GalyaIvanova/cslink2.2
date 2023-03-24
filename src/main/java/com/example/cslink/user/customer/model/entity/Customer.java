package com.example.cslink.user.customer.model.entity;

import com.example.cslink.user.model.entity.Phone;
import com.example.cslink.user.model.entity.UserProfile;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


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
}