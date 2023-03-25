package com.example.cslink.user.model.dataccess.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.cslink.user.model.entity.Cosmetologist;

@Repository
@EnableJpaRepositories
public interface CosmetologistDao extends JpaRepository<Cosmetologist, Long> {


}
