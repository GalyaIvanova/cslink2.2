package com.example.cslink.user.controller.mappers;

import com.example.cslink.user.model.dto.CustomerDto;
import com.example.cslink.user.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cslink.user.controller.service.UserProfileService;

@Component
public class CustomerAssembler {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileMapper UserProfileMapper;

    @Autowired
    private CustomerMapper customerMapper;

    public CustomerDto toDtoModel(Customer client) {
        CustomerDto clientDTO = customerMapper.toDtoModel(client);
        return clientDTO;
    }

    public Customer toClientEntity(CustomerDto clientDTO) {
        Customer client = customerMapper.toClientEntity(clientDTO);
        client.setUserProfile(UserProfileMapper.toEntity(userProfileService.getUserProfileById(client.getId())));
        return client;
    }
}
