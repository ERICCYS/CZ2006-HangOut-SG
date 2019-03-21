package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class CustomerController {

    // TODO:  Change http status for different exceptions

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return JSONConvert.JSONConverter(customers);
    }
    // pass

    @GetMapping("/customer")
    public String getCustomerById(
            @RequestParam Long customerId
    ) {
        Customer customer = customerService.findCustomerById(customerId);
        return JSONConvert.JSONConverter(customer);
    }
    // pass

    @GetMapping("/customer/signin")
    public String signInCustomer(
            @RequestParam String email,
            @RequestParam String password
    ) throws NoSuchAlgorithmException {
        Customer customer = customerService.findCustomerByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException();
        }
        return ValidationController.UserSignIn(customer, password);
    }
    // pass


    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(
            @Valid @RequestBody Customer customer
    ) throws NoSuchAlgorithmException {
        String hashedPassword = customer.hashPassword(customer.getPassword());
        customer.setPassword(hashedPassword);
        JSONConvert.JSONConverter(customerService.save(customer));
        return ValidationController.getAccessToken(customer.getId(), "CUSTOMER");
    }
    // pass

    @PutMapping("/customer")
    public String updateCustomerById(
            @RequestParam Long customerId,
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody Customer customer
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            Customer oldCustomer = customerService.findCustomerById(customerId);
            oldCustomer.setFirstName(customer.getFirstName());
            oldCustomer.setLastName(customer.getLastName());
            oldCustomer.setGender(customer.getGender());
            oldCustomer.setDob(customer.getDob());
            oldCustomer.setAvatar(customer.getAvatar());
            oldCustomer.setHalaPreference(customer.isHalaPreference());
            oldCustomer.setVegPreference(customer.isVegPreference());
            oldCustomer.setRegionalPreference(customer.getRegionalPreference());
            customerService.save(oldCustomer);
            return "OK";
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    //pass

    @DeleteMapping("/customer")
    public String deleteCustomer(
            @RequestParam Long customerId,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);
        if (info.length != 2)
            throw new IllegalArgumentException();
        if (Long.parseLong(info[0]) == customerId && info[1].equals("CUSTOMER")) {
            Customer customer = customerService.findCustomerById(customerId);
            customerService.delete(customer);
            return "OK";
        } else {
            throw new IllegalArgumentException();
        }
    }
    //pass

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED,
            reason = "Email or password incorrect")
    @ExceptionHandler(IllegalAccessException.class)
    public void badAuthenticationException() {

    }
}
