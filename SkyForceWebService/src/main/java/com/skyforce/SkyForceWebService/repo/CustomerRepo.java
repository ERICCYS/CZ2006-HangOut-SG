package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    // find all the customers
    Customer findById(Long id);

    // insert one user
    Customer save(Customer customer);

    // delete one user
    void delete(Customer customer);

}
