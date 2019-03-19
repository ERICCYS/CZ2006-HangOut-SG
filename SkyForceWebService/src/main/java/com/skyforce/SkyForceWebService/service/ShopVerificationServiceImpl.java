package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.ShopVerification;
import com.skyforce.SkyForceWebService.repo.ShopVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopVerificationServiceImpl implements ShopVerificationService {

    @Autowired
    ShopVerificationRepo shopVerificationRepo;

    @Override
    public List<ShopVerification> findAll() {
        return shopVerificationRepo.findAll();
    }

    @Override
    public ShopVerification findById(Long id) {
        return shopVerificationRepo.findById(id);
    }

    @Override
    public ShopVerification save(ShopVerification shopVerification) {
        return shopVerificationRepo.save(shopVerification);
    }

    @Override
    public void delete(ShopVerification shopVerification) {
        shopVerificationRepo.delete(shopVerification);
    }

}
