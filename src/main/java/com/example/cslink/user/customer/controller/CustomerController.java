package com.example.cslink.user.customer.controller;

import com.example.cslink.user.customer.controller.service.CustomerService;
import com.example.cslink.user.customer.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService clientService;

    @GetMapping("/{id}")
    public Customer getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping("/reate/{id}")
    public Customer createClient(@RequestBody Customer client) {
        return clientService.createClient(client);
    }

    @PutMapping("/update/{id}")
    public Customer updateClient(@PathVariable Long id, @RequestBody Customer client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClientById(id);
    }


}
