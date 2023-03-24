package com.example.cslink.tenant.controller;

import com.example.cslink.tenant.controller.service.TenantService;
import com.example.cslink.tenant.model.dto.TenantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tenants")
@PreAuthorize("hasRole('ROLE_TENANT')")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping("/{tenantId}")
    public ResponseEntity<TenantDto> getTenantById(@PathVariable Long tenantId) {
        TenantDto tenant=tenantService.getTenantById(tenantId);
        return ResponseEntity.ok(tenant);
    }

    @PostMapping("/create")
    public ResponseEntity<TenantDto> createTenant(@RequestBody TenantDto tenantDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantService.createTenant(tenantDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TenantDto> updateTenantById(@PathVariable Long id, @RequestBody TenantDto tenantDto) {
        return ResponseEntity.ok().body(tenantService.updateTenant(id, tenantDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTenantById(@PathVariable Long id) {
        tenantService.deleteTenantById(id);
        return ResponseEntity.noContent().build();
    }
}

