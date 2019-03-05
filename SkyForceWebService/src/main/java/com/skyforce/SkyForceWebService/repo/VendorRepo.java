package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {

    // find the vendor by id
    Vendor findById(Long id);

    // find the vendor by email
    Vendor findByEmail(String email);

    // insert one user
    Vendor save(Vendor vendor);

    // delete one user
    void delete(Vendor vendor);
}
