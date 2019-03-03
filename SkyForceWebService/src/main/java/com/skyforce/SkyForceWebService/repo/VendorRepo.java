package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {

    // find all the vendor
    Vendor findById(Long id);

    // insert one user
    Vendor save(Vendor vendor);

    // delete one user
    void delete(Vendor vendor);
}
