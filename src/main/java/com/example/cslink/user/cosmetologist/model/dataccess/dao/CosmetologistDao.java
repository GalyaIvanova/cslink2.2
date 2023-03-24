package com.example.cslink.user.cosmetologist.model.dataccess.dao;

import com.example.cslink.user.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CosmetologistDao extends JpaRepository<Cosmetologist, Long> {


}
