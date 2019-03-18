package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.ShopVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopVerificationRepo extends JpaRepository<ShopVerification, Integer> {

    // find By Id
    ShopVerification findById (Long id);

    // save a shop verification
    ShopVerification save (ShopVerification shopVerification);

    // delete a shop verification
    void delete (ShopVerification shopVerification);
}
