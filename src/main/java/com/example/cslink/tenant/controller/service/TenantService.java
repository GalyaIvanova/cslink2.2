package com.example.cslink.tenant.controller.service;

import com.example.cslink.tenant.model.dto.TenantDto;

import java.util.List;

public interface TenantService {
    TenantDto createTenant(TenantDto tenantDto);

    TenantDto updateTenant(Long tenantId, TenantDto tenantDto);

    void deleteTenantById(Long tenantId);

    TenantDto getTenantById(Long tenantId);

    List<TenantDto> getAllTenants();
}
