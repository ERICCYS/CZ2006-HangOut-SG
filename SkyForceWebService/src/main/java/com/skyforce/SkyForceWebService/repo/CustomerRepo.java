package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Customer;
import com.skyforce.SkyForceWebService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    // find all the users

    // insert one user

    // find user by email
    Customer findById(Long id);

    Customer save(Customer customer);

    void delete(Customer customer);
}
