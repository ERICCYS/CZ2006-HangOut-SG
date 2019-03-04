package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shop, Integer> {
    // find all the shop
    Shop findById(Long id);

    // insert one user
    Shop save(Shop shop);

    // delete one user
    void delete(Shop shop);
}
