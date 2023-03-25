package com.example.cslink.user.controller.mappers;


import org.springframework.stereotype.Component;

import com.example.cslink.user.model.dto.CustomerDto;
import com.example.cslink.user.model.entity.Customer;
@Component
public interface CustomerMapper {
    CustomerDto toDtoModel(Customer client);

    Customer toClientEntity(CustomerDto clientDTO);
}
