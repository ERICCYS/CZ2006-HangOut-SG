package com.skyforce.SkyForceWebService.repo;

import com.skyforce.SkyForceWebService.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepo extends JpaRepository<Shop, Integer> {

    Shop findById(Long id);

    Shop save(Shop shop);

    void delete(Shop shop);
}
