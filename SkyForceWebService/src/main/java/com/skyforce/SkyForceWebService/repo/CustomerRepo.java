package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findById(Long id);

    Customer findByEmail(String email);

    Customer save(Customer customer);

    void delete(Customer customer);
}
