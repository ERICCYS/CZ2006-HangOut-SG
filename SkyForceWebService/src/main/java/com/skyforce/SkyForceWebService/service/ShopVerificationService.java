package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.ShopVerification;

import java.util.List;


public interface ShopVerificationService {

    List<ShopVerification> findAll();

    ShopVerification findById(Long id);

    ShopVerification save(ShopVerification shopVerification);

    void delete(ShopVerification shopVerification);
}
