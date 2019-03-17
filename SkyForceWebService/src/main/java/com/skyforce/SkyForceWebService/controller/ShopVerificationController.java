package com.skyforce.SkyForceWebService.controller;

import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.model.ShopVerification;
import com.skyforce.SkyForceWebService.service.ShopService;
import com.skyforce.SkyForceWebService.service.ShopVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ShopVerificationController {

    @Autowired
    ShopService shopService;

    @Autowired
    ShopVerificationService shopVerificationService;

    private AtomicLong nextId = new AtomicLong();

    // Get all shop verifications
    // http://localhost:9090/api/verification
    @GetMapping("/verification")
    public String getAllShopVerification() {

        return JSONConvert.JSONConverter(shopVerificationService.findAll());
    }

    // Add shop verification
    // http://localhost:9090/api/shop/1/verify
    @PostMapping("/shop/{id}/verify")
    @ResponseStatus(HttpStatus.CREATED)
    public String postShopVerification(@PathVariable("id") Long id, @Valid @RequestBody ShopVerification shopVerification) {
        Shop shop = shopService.findShopById(id);
        shopVerification.setId(nextId.incrementAndGet());
        shopVerification.setShop(shop);
        shopVerification.setVendor(shop.getVendor());
        shopVerification.setProcessed(false);
        shopVerification.setApproved(false);
        return JSONConvert.JSONConverter(shopVerificationService.save(shopVerification));
    }


    // Admin process the verification
    // Example
    // http://localhost:9090/api/verification/1/false
    @PutMapping("/verification/{id}/{state}")
    public String manageShopVerification(@PathVariable("id") Long id, @PathVariable("state") boolean state) {
        ShopVerification shopVerification = shopVerificationService.findById(id);
        shopVerification.setProcessed(true);
        shopVerification.setApproved(state);
        shopVerification.getShop().setVerified(state);
        return JSONConvert.JSONConverter(shopVerificationService.save(shopVerification));
    }
}
