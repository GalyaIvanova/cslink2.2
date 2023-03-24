package com.example.cslink.tenant.controller.converter;

import com.example.cslink.tenant.model.dto.TenantDto;
import com.example.cslink.tenant.model.entity.Tenant;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Component
public interface TenantMapper {

    TenantDto toDto(Tenant tenant);

    Tenant toEntity(TenantDto dto);

    List<TenantDto> toDtoList(List<Tenant> tenants);

    List<Tenant> toEntityList(List<TenantDto> dtos);
}
