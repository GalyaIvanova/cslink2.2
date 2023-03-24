package com.example.cslink.user.cosmetologist.controller.service;

import com.example.cslink.user.cosmetologist.model.dto.CosmetologistDto;
import com.example.cslink.user.cosmetologist.model.entity.Cosmetologist;
import com.example.cslink.procedure.model.dto.ProcedureDto;
import com.example.cslink.user.customer.model.dto.CustomerDto;

import java.util.List;

public interface CosmetologistService {
    List<CosmetologistDto> getAllCosmetologists();

    Cosmetologist getCosmetologistById(Long cosmetologistId);

    CosmetologistDto createCosmetologist(CosmetologistDto cosmetologistDTO);

    CosmetologistDto updateCosmetologist(Long cosmetologistId, CosmetologistDto cosmetologistDTO);

    void deleteCosmetologist(Long cosmetologistId);

    List<CustomerDto> getCustomersForCosmetologist(Long cosmetologistId);

    void addCustomerToCosmetologist(Long cosmetologistId, Long CustomerId);

    List<ProcedureDto> getProceduresForCosmetologist(Long cosmetologistId);

    Cosmetologist addProcedureToCosmetologist(Long cosmetologistId, Long procedureId);

    void removeCustomerFromCosmetologist(Long cosmetologistId, Long customerId);

    Cosmetologist removeProcedureFromCosmetologist(Long cosmetologistId, Long procedureId);
    // List<Customer> getCustomersForCosmetologist(Long cosmetologistId);
}
