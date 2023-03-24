package com.example.cslink.tenant.controller.converter.impl;

import com.example.cslink.tenant.controller.converter.TenantMapper;
import com.example.cslink.tenant.model.dto.TenantDto;
import com.example.cslink.tenant.model.entity.Tenant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenantMapperImpl implements TenantMapper {

    @Override
    public TenantDto toDto(Tenant tenant) {
        TenantDto tenantDto=new TenantDto();
        tenantDto.setId(tenant.getId());
        tenantDto.setName(tenant.getName());
        tenantDto.setDomain(tenant.getDomain());
        return tenantDto;
    }

    @Override
    public Tenant toEntity(TenantDto tenantDto) {
        Tenant tenant=new Tenant();
        tenant.setId(tenantDto.getId());
        tenant.setName(tenantDto.getName());
        tenant.setDomain(tenantDto.getDomain());
        return tenant;
    }

    @Override
    public List<TenantDto> toDtoList(List<Tenant> tenants) {
        if (tenants == null) {
            return null;
        }

        List<TenantDto> tenantDtos=new ArrayList<>();

        for (Tenant tenant : tenants) {
            tenantDtos.add(toDto(tenant));
        }

        return tenantDtos;
    }

    @Override
    public List<Tenant> toEntityList(List<TenantDto> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}


