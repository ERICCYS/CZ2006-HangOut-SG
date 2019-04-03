package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.model.Vendor;
import com.skyforce.SkyForceWebService.service.ProductService;
import com.skyforce.SkyForceWebService.service.ShopService;
import com.skyforce.SkyForceWebService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    VendorService vendorService;

    @Autowired
    ShopService shopService;

    @Autowired
    ProductService productService;

    @GetMapping("/shops")
    public String getAllShops() {
        List<Shop> shops = shopService.findAll();
        return JSONConvert.JSONConverter(shops);
    }

    @GetMapping("/shop")
    public String getShopById(
            @RequestParam Long shopId
    ) {
        Shop shop = shopService.findShopById(shopId);
        return JSONConvert.JSONConverter(shop);
    }

    @PostMapping("/vendor/shop")
    @ResponseStatus(HttpStatus.CREATED)
    public String createShop(
            @RequestParam Long vendorId,
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody Shop shop
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (info.length != 2)
            throw new IllegalArgumentException();

        if (Long.parseLong(info[0]) == vendorId && info[1].equals("VENDOR")) {
            Vendor oldVendor = vendorService.findVendorById(vendorId);
            oldVendor.addShop(new Shop(shop.getName(), shop.getContactNumber(), shop.getContactEmail(), shop.getCategory(), shop.getLocation(), shop.getCarParkNumbers()));
            return JSONConvert.JSONConverter(vendorService.save(oldVendor));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/vendor/shop")
    public String deleteShop(
            @RequestParam Long vendorId,
            @RequestParam Long shopId,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (info.length != 2)
            throw new IllegalArgumentException();

        if (Long.parseLong(info[0]) == vendorId && info[1].equals("VENDOR")) {
            Vendor oldVendor = vendorService.findVendorById(vendorId);
            Shop shop = shopService.findShopById(shopId);
            oldVendor.removeShop(shop);
            return JSONConvert.JSONConverter(vendorService.save(oldVendor));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @PutMapping("/shop")
    public String updateShopById(
            @RequestParam Long shopId,
            @Valid @RequestBody Shop shop,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        Shop oldShop = shopService.findShopById(shopId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (oldShop == null || info.length != 2) {
            throw new IllegalArgumentException();
        }

        if (Long.parseLong(info[0]) == oldShop.getVendor().getId() && info[1].equals("VENDOR")) {
            oldShop.setName(shop.getName());
            oldShop.setCertificate(shop.getCertificate());
            oldShop.setContactEmail(shop.getContactEmail());
            oldShop.setContactNumber(shop.getContactNumber());
            Shop updatedShop = shopService.save(shop);
            return JSONConvert.JSONConverter(updatedShop);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
