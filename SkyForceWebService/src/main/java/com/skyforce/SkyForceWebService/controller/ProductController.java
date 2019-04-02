package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Product;
import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.service.ProductService;
import com.skyforce.SkyForceWebService.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ShopService shopService;

    @GetMapping("/test")
    public String foo(@RequestParam String id) {
        return "ID " + id;
    }

    @GetMapping("/products")
    public String getAllProducts() {
        List<Product> products = productService.findAll();
        return JSONConvert.JSONConverter(products);
    }

    @GetMapping("/product")
    public String getProductById(
            @RequestParam Long id
    ) {
        Product product = productService.findProductById(id);
        return JSONConvert.JSONConverter(product);
    }

    @PostMapping("/shop/product")
    public String createProduct(
            @RequestParam Long shopId,
            @Valid @RequestBody Product product,
            @RequestHeader(value = "Authorization") String accessToken
    ) {

        Shop oldShop = shopService.findShopById(shopId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (oldShop == null || info.length != 2) {
            throw new IllegalArgumentException();
        }
        if (Long.parseLong(info[0]) == oldShop.getVendor().getId() && info[1].equals("VENDOR")) {
            oldShop.addProduct(new Product(product.getName(), product.getDescription(), product.getProductImg()));
            return "OK";
        } else {
            throw new IllegalArgumentException();
        }


    }

    @DeleteMapping("/shop/product")
    public String deleteProduct(
            @RequestParam Long shopId,
            @RequestParam Long productId,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        Shop oldShop = shopService.findShopById(shopId);
        String[] info = ValidationController.decryptAccessToken(accessToken);

        if (oldShop == null || info.length != 2) {
            throw new IllegalArgumentException();
        }

        if (Long.parseLong(info[0]) == oldShop.getVendor().getId() && info[1].equals("VENDOR")) {
            Product product = productService.findProductById(productId);
            oldShop.removeProduct(product);
            return "OK";
        } else {
            throw new IllegalArgumentException();
        }
    }
}

