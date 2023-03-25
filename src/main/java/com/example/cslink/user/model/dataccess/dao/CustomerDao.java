package com.example.cslink.user.model.dataccess.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cslink.user.model.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
}