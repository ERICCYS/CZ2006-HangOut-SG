package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shop, Integer> {

    // find a shop by Id
    Shop findById(Long id);

    // insert one shop
    Shop save(Shop shop);

    // delete one shop
    void delete(Shop shop);
}
