package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {

    // TODO:  Change http status for different exceptions


    @Autowired
    CustomerService customerService;
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/customer")
    public String getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return "there are a brunch of customers " + customers;
    }

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        return "find customer by id: " + customer;
    }

    @GetMapping("/customer/signin/{email}/{password}")
    public Customer signInCustomer (@PathVariable("email") String email, @PathVariable("password") String password) throws NoSuchAlgorithmException {
        Customer customer = customerService.findCustomerByEmail(email);
        String hashedPassword = customer.hashPassword(password);
        if (hashedPassword.equals(customer.getPassword())) {
            return customer;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED,
            reason = "Email or password incorrect")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }


    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@Valid @RequestBody Customer customer) throws NoSuchAlgorithmException {
        customer.setId(nextId.incrementAndGet());
        String hashedPassword = customer.hashPassword(customer.getPassword());
        customer.setPassword(hashedPassword);
        return "Post success" + customerService.save(customer);
    }

    @PutMapping("/customer/{id}")
    public String updateCustomerById(@PathVariable("id") Long id, @Valid @RequestBody Customer customer) {
        Customer oldCustomer = customerService.findCustomerById(id);
        oldCustomer.setFirstName(customer.getFirstName());
        oldCustomer.setLastName(customer.getLastName());
        oldCustomer.setGender(customer.getGender());
        oldCustomer.setDob(customer.getDob());
        oldCustomer.setAvatar(customer.getAvatar());
        oldCustomer.setHalaPreference(customer.isHalaPreference());
        oldCustomer.setVegPreference(customer.isVegPreference());
        oldCustomer.setRegionalPreference(customer.getRegionalPreference());
        Customer updatedCustomer = customerService.save(oldCustomer);
        return "updated Customer" + updatedCustomer;
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        customerService.delete(customer);
        return ResponseEntity.ok().build();
    }
}
