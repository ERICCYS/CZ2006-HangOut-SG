package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, Integer> {

    Vendor findById(Long id);

    Vendor findByEmail(String email);

    Vendor save(Vendor vendor);

    void delete(Vendor vendor);
}
