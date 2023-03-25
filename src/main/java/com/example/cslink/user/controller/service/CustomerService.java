package com.example.cslink.user.controller.service;

import java.util.List;

import com.example.cslink.user.model.entity.Customer;
import com.example.cslink.exceptions.CustomResourceNotFoundException;

public interface CustomerService {
    Customer getClientById(Long id) throws CustomResourceNotFoundException;

    Customer createClient(Customer client);

    Customer updateClient(Long id, Customer client);

    void deleteClientById(Long id) throws CustomResourceNotFoundException;

    List<Customer> getAllClients();

    Customer saveClient(Customer client);

}
