package com.example.cslink.user.customer.controller.mappers.impl;

import com.example.cslink.user.customer.controller.mappers.CustomerMapper;
import com.example.cslink.user.customer.model.dto.CustomerDTO;
import com.example.cslink.user.customer.model.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

   @Override
    public CustomerDTO toDtoModel(Customer client) {
        CustomerDTO clientDTO = new CustomerDTO();
        clientDTO.setId(client.getId());
        clientDTO.setUserProfileId(client.getUserProfile().getId());
        //        clientDTO.setCreatedAt(client.getCreatedAt());
        return clientDTO;
    }

    @Override
    public Customer toClientEntity(CustomerDTO clientDTO) {
        Customer client = new Customer();
        client.setId(clientDTO.getId());
       // client.setUserProfile(UserProfileMapper.toEntity(userProfileService.getUserProfileById(clientDTO.getUserProfileId())));
        return client;
    }

}
