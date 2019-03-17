package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
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

    @GetMapping("/customers")
    public String getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        return JSONConvert.JSONConverter(customers);
    }

    @GetMapping("/customer")
    public String getCustomerById(
            @RequestParam Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return JSONConvert.JSONConverter(customer);
    }

    @GetMapping("/customer/signin/{email}/{password}")
    public String signInCustomer (@PathVariable("email") String email, @PathVariable("password") String password) throws NoSuchAlgorithmException {
        Customer customer = customerService.findCustomerByEmail(email);
        return ValidationController.UserSignIn(customer, password);
    }
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED,
            reason = "Email or password incorrect")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(
            @Valid @RequestBody Customer customer
    ) throws NoSuchAlgorithmException {
        customer.setId(nextId.incrementAndGet());
        String hashedPassword = customer.hashPassword(customer.getPassword());
        customer.setPassword(hashedPassword);
        return JSONConvert.JSONConverter(customerService.save(customer));
    }

    @PutMapping("/customer")
    public String updateCustomerById(
            @RequestParam Long customerId,
            @Valid @RequestBody Customer customer
    ) {
        Customer oldCustomer = customerService.findCustomerById(customerId);
        oldCustomer.setFirstName(customer.getFirstName());
        oldCustomer.setLastName(customer.getLastName());
        oldCustomer.setGender(customer.getGender());
        oldCustomer.setDob(customer.getDob());
        oldCustomer.setAvatar(customer.getAvatar());
        oldCustomer.setHalaPreference(customer.isHalaPreference());
        oldCustomer.setVegPreference(customer.isVegPreference());
        oldCustomer.setRegionalPreference(customer.getRegionalPreference());
        Customer updatedCustomer = customerService.save(oldCustomer);
        return JSONConvert.JSONConverter(updatedCustomer);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<?> deleteCustomer(
            @RequestParam Long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        customerService.delete(customer);
        return ResponseEntity.ok().build();
    }
}
