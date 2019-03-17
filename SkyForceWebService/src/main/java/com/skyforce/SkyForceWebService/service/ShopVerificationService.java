package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.ShopVerification;

import java.util.List;


public interface ShopVerificationService {

    // find all
    List<ShopVerification> findAll();

    // find By Id
    ShopVerification findById (Long id);

    // save a shop verification
    ShopVerification save (ShopVerification shopVerification);

    // delete a shop verification
    void delete (ShopVerification shopVerification);
}
