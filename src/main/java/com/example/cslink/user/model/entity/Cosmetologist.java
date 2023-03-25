package com.example.cslink.user.model.entity;

import com.example.cslink.management.schedule.model.entity.Availability;
import com.example.cslink.procedure.model.Procedure;
import com.example.cslink.user.model.entity.Customer;
import com.example.cslink.user.model.entity.UserProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cosmetologist")
public class Cosmetologist {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String IBAN;

    private String specialty;

    private Integer yearsOfExperience;
    @OneToMany(mappedBy="cosmetologist_id", cascade=CascadeType.ALL)
    private List<Availability> availability;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userProfile_id", referencedColumnName="id")
    private UserProfile userProfile;

    @JsonIgnore
    @OneToMany(mappedBy="cosmetologists", cascade=CascadeType.ALL)
    private List<Procedure> procedures;


    @JsonIgnore
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="cosmetologist_customer",
            joinColumns=@JoinColumn(name="cosmetologist_id"),
            inverseJoinColumns=@JoinColumn(name="customer_id"))
    private List<Customer> customers;

//    @OneToMany(mappedBy="cosmetologist", cascade=CascadeType.ALL)
//    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN=IBAN;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile=userProfile;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures=procedures;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers=customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public boolean removeCustomer(Long customerId) {
       return this.customers.removeIf(c -> Objects.equals(customerId, c.getId()));
    }

//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<Transaction> transactions) {
//        this.transactions=transactions;
//    }

    public String getPhoneNumber() {
        return this.userProfile.getPhone().getNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.userProfile.getPhone().setNumber(phoneNumber);
    }

    public void addProcedure(Procedure procedure) {
        this.procedures.add(procedure);
    }

    public boolean removeProcedure(Long procedureId) {
        return this.procedures.removeIf(p -> Objects.equals(p.getId(), procedureId));
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability=availability;
    }

    public void addAvailability(Availability availability) {
        this.availability.add(availability);
    }

    public boolean removeAvailability(Availability availability) {
        return this.availability.remove(availability);
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty=specialty;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience=yearsOfExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cosmetologist that=(Cosmetologist) o;
        return id.equals(that.id)
                && Objects.equals(IBAN, that.IBAN)
                && Objects.equals(specialty, that.specialty)
                && Objects.equals(yearsOfExperience, that.yearsOfExperience)
                && Objects.equals(availability, that.availability)
                && userProfile.equals(that.userProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, IBAN, specialty, yearsOfExperience, userProfile);
    }

    @Override
    public String toString() {
        return "Cosmetologist{" +
                "id=" + id +
                ", IBAN='" + IBAN + '\'' +
                ", specialty='" + specialty + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", userProfile=" + userProfile +
                '}';
    }
}