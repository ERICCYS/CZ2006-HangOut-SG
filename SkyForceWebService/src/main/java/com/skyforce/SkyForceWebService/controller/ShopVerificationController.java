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

@RestController
public class ShopVerificationController {

    @Autowired
    ShopService shopService;

    @Autowired
    ShopVerificationService shopVerificationService;

    // Get all shop verifications
    // http://localhost:9090/api/verification
    @GetMapping("/verifications")
    public String getAllShopVerification() {
        return JSONConvert.JSONConverter(shopVerificationService.findAll());
    }

    // Add shop verification
    // http://localhost:9090/api/shop/1/verify
    @PostMapping("/shop/verify")
    @ResponseStatus(HttpStatus.CREATED)
    public String postShopVerification(
            @RequestParam Long shopId,
            @Valid @RequestBody ShopVerification shopVerification
    ) {
        Shop shop = shopService.findShopById(shopId);
        shopVerification.setShop(shop);
        shopVerification.setVendor(shop.getVendor());
        shopVerification.setProcessed(false);
        shopVerification.setApproved(false);
        return JSONConvert.JSONConverter(shopVerificationService.save(shopVerification));
    }


    @PutMapping("/verification/{id}/{state}")
    public String manageShopVerification(
            @RequestParam Long shopVerificationId,
            @RequestParam boolean state,
            @RequestHeader(value = "Authorization") String accessToken
    ) {

        ShopVerification shopVerification = shopVerificationService.findById(shopVerificationId);
        shopVerification.setProcessed(true);
        shopVerification.setApproved(state);
        shopVerification.getShop().setVerified(state);
        return JSONConvert.JSONConverter(shopVerificationService.save(shopVerification));
    }
}
