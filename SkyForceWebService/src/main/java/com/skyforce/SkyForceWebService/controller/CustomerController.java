package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/customer")
    public String userAll() {
        List<Customer> customers = customerService.findAll();
        return "there are a brunch of customers " + customers;
    }

    @GetMapping("/customer/{id}")
    public String getUserById(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        return "find customer by id: " + customer;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@Valid @RequestBody Customer customer) {
        customer.setId(nextId.incrementAndGet());
        return "Post success" + customerService.save(customer);
    }

    @PutMapping("/customer/{id}")
    public String updateUserById(@PathVariable("id") Long id, @Valid @RequestBody Customer customer) {

        Customer oldCustomer = customerService.findCustomerById(id);

        oldCustomer.setFirstName(customer.getFirstName());
        oldCustomer.setLastName(customer.getLastName());
        oldCustomer.setGender(customer.getGender());

        Customer updatedUser = customerService.save(oldCustomer);
        return "updated Customer" + updatedUser;
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long userId) {
        Customer customer = customerService.findCustomerById(userId);
        customerService.delete(customer);
        return ResponseEntity.ok().build();
    }
}
