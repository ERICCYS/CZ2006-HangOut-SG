package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepo.delete(customer);
    }
}
