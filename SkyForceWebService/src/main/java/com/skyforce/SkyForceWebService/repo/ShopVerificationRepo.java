package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.ShopVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopVerificationRepo extends JpaRepository<ShopVerification, Integer> {

    ShopVerification findById(Long id);

    ShopVerification save(ShopVerification shopVerification);

    void delete(ShopVerification shopVerification);
}
