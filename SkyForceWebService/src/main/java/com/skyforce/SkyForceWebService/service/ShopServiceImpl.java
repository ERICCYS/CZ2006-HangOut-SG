package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.repo.ShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepo shopRepo;

    @Override
    public List<Shop> findAll() {
        return shopRepo.findAll();
    }

    @Override
    public Shop findShopById(Long id) {
        return shopRepo.findById(id);
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepo.save(shop);
    }

    @Override
    public void delete(Shop shop) {
        shopRepo.delete(shop);
    }
}
