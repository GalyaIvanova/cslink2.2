package com.example.cslink.user.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cslink.user.customer.controller.service.CustomerService;
import com.example.cslink.user.customer.model.entity.Customer;

@RestController
@RequestMapping("/clients")
public class CustomerController {

    @Autowired
    private CustomerService clientService;

    @GetMapping("/{id}")
    public Customer getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Customer createClient(@RequestBody Customer client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Customer updateClient(@PathVariable Long id, @RequestBody Customer client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }


}
