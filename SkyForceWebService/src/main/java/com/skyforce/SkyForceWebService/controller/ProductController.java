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
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ShopService shopService;

    private AtomicLong nextId = new AtomicLong();

    // How to use the request params
    // Params key value pair, key must be the parameter id
    @GetMapping("/test")
    public String foo(@RequestParam String id) {
        return "ID " + id;
    }

    // How to use the request header
    // Header key must be the requestHeader value here
//    @GetMapping("/headertest")
//    public String fooo(
//            @RequestHeader(value = "Accept") String accept,
//            @RequestHeader(value = "Accept-Language") String acceptLanguage,
//            @RequestHeader(value = "User-Agent", defaultValue = "foo") String userAgent,
//            @RequestHeader(value = "Authorization") String accessToken
//    ) {
//        System.out.println("accept: " + accept);
//        System.out.println("acceptLanguage: " + acceptLanguage);
//        System.out.println("userAgent: " + userAgent);
//        System.out.println("authentication " + accessToken);
//        return accept + " " + acceptLanguage + " " + userAgent + " " + accessToken;
//    }

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

    // Shop add product
    @PostMapping("/shop/product")
    public String createProduct(
            @RequestParam Long shopId,
            @Valid @RequestBody Product product
    ) {
        Shop oldShop = shopService.findShopById(shopId);
//        product.setId(nextId.incrementAndGet());
        oldShop.addProduct(new Product(product.getName(), product.getDescription(), product.getProductImg()));
        return "OK";
    }

    // Shop delete product
    @DeleteMapping("/shop/product")
    public String deleteProduct(
            @RequestParam Long shopId,
            @RequestParam Long productId
    ) {
        Shop oldShop = shopService.findShopById(shopId);
        Product product = productService.findProductById(productId);
        oldShop.removeProduct(product);
        return "OK";
    }

}

