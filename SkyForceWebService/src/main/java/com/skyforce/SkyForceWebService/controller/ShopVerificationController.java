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

    @GetMapping("/verifications")
    public String getAllShopVerification() {
        return JSONConvert.JSONConverter(shopVerificationService.findAll());
    }

    @PostMapping("/shop/verify")
    @ResponseStatus(HttpStatus.CREATED)
    public String postShopVerification(
            @RequestParam Long shopId,
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody ShopVerification shopVerification
    ) {

        Shop shop = shopService.findShopById(shopId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (shop == null || info.length != 2) {
            throw new IllegalArgumentException();
        }

        if (Long.parseLong(info[0]) == shop.getVendor().getId() && info[1].equals("VENDOR")) {
            shopVerification.setShop(shop);
            shopVerification.setVendor(shop.getVendor());
            shopVerification.setProcessed(false);
            shopVerification.setApproved(false);
            return JSONConvert.JSONConverter(shopVerificationService.save(shopVerification));
        }
        else {
            throw new IllegalArgumentException();
        }

    }


    @PutMapping("/verification/{id}/{state}")
    public String manageShopVerification(
            @RequestParam Long shopVerificationId,
            @RequestParam boolean state,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        ShopVerification shopVerification = shopVerificationService.findById(shopVerificationId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (shopVerification == null || info.length == 2)
            throw new IllegalArgumentException();

        if (info[1].equals("ADMIN")) {
            shopVerification.setProcessed(true);
            shopVerification.setApproved(state);
            shopVerification.getShop().setVerified(state);
            return JSONConvert.JSONConverter(shopVerificationService.save(shopVerification));
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
