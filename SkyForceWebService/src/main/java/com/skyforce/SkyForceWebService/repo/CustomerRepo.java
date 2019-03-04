package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    // find a customer by id
    Customer findById(Long id);

    // find a customer by email
    Customer findByEmail(String email);

    // insert one user
    Customer save(Customer customer);

    // delete one user
    void delete(Customer customer);

}
