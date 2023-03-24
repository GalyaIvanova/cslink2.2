package com.example.cslink.user.customer.controller.mappers;


import org.springframework.stereotype.Component;

import com.example.cslink.user.customer.model.dto.CustomerDto;
import com.example.cslink.user.customer.model.entity.Customer;
@Component
public interface CustomerMapper {
    CustomerDto toDtoModel(Customer client);

    Customer toClientEntity(CustomerDto clientDTO);
}
