package com.example.cslink.user.customer.controller.mappers;


import org.springframework.stereotype.Component;

import com.example.cslink.user.customer.model.dto.CustomerDTO;
import com.example.cslink.user.customer.model.entity.Customer;
@Component
public interface CustomerMapper {
    CustomerDTO toDtoModel(Customer client);

    Customer toClientEntity(CustomerDTO clientDTO);
}
