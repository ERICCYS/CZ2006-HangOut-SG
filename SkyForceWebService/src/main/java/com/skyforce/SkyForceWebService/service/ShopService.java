package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> findAll();

    Shop findShopById(Long id);

    Shop save(Shop shop);

    void delete(Shop shop);
}
