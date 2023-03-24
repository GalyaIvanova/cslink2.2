package com.example.cslink.user.customer.controller.mappers.impl;

import com.example.cslink.user.customer.controller.mappers.CustomerMapper;
import com.example.cslink.user.customer.model.dto.CustomerDto;
import com.example.cslink.user.customer.model.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDto toDtoModel(Customer client) {
        CustomerDto clientDto=new CustomerDto();
        clientDto.setId(client.getId());
        clientDto.setUserProfileId(client.getUserProfile().getId());
        clientDto.setCreatedAt(client.getCreatedAt());
        return clientDto;
    }

    @Override
    public Customer toClientEntity(CustomerDto clientDto) {
        Customer client=new Customer();
        client.setId(clientDto.getId());
        // client.setUserProfile(UserProfileMapper.toEntity(userProfileService.getUserProfileById(clientDTO.getUserProfileId())));
        client.setCreatedAt(clientDto.getCreatedAt());
        return client;
    }

}
