package com.example.cslink.tenant.model.dao;

import com.example.cslink.tenant.model.entity.Tenant;

import java.util.List;
import java.util.Optional;

public interface TenantDao {

    Optional<Tenant> findById(Long id);

    Optional<Tenant> findByName(String name);

    Tenant save(Tenant tenant);

    void delete(Tenant tenant);

    List<Tenant> findAll();
}

