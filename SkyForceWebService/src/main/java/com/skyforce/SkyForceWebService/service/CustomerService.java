package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);

    Customer save(Customer customer);

    void delete(Customer customer);
}
