package com.example.cslink.tenant.controller.service.impl;

import com.example.cslink.tenant.controller.converter.TenantMapper;
import com.example.cslink.tenant.controller.service.TenantService;
import com.example.cslink.tenant.model.dao.TenantDao;
import com.example.cslink.tenant.model.dto.TenantDto;
import com.example.cslink.tenant.model.entity.Tenant;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantDao tenantRepository;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List<TenantDto> getAllTenants() {
        List<Tenant> tenants=tenantRepository.findAll();
        return tenants.stream().map(tenantMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TenantDto createTenant(TenantDto TenantDto) {
        Tenant tenant=new Tenant(TenantDto.getName(), TenantDto.getDomain());
        Tenant savedTenant=tenantRepository.save(tenant);
        return tenantMapper.toDto(savedTenant);
    }

    @Override
    public TenantDto updateTenant(Long tenantId, TenantDto TenantDto) {
        Optional<Tenant> optionalTenant=tenantRepository.findById(tenantId);
        if (optionalTenant.isPresent()) {
            Tenant tenant=optionalTenant.get();
            tenant.setName(TenantDto.getName());
            tenant.setDomain(TenantDto.getDomain());
            Tenant updatedTenant=tenantRepository.save(tenant);
            return tenantMapper.toDto(updatedTenant);
        }
        throw new EntityNotFoundException("Tenant not found with id: " + tenantId);
    }

    @Override
    public void deleteTenantById(Long tenantId) {
        Optional<Tenant> optionalTenant=tenantRepository.findById(tenantId);
        if (optionalTenant.isPresent()) {
            tenantRepository.delete(optionalTenant.get());
        } else {
            throw new EntityNotFoundException("Tenant not found with id: " + tenantId);
        }
    }

    @Override
    public TenantDto getTenantById(Long tenantId) {
        return null;
    }
}
