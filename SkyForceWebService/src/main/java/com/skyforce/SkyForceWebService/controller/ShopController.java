package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Product;
import com.skyforce.SkyForceWebService.model.Shop;
import com.skyforce.SkyForceWebService.service.ProductService;
import com.skyforce.SkyForceWebService.service.ShopService;
import com.skyforce.SkyForceWebService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ShopController {
    // TODO: all these need to check user's credential

    // TODO: change shop's other information
    // TODO: add product to shop
    // TODO: delete product from shop

    @Autowired
    VendorService vendorService;

    @Autowired
    ShopService shopService;

    @Autowired
    ProductService productService;

    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/shop")
    public String getAllShops() {
        List<Shop> shops = shopService.findAll();
        return JSONConvert.JSONConverter(shops);
    }

    @GetMapping("/shop/{id}")
    public String getShopById(@PathVariable("id") Long id) {
        Shop shop = shopService.findShopById(id);
        return JSONConvert.JSONConverter(shop);
    }

    // Change shop information
    @PutMapping("/shop/{id}")
    public String updateShopById(@PathVariable("id") Long id, @Valid @RequestBody Shop shop) {
        Shop oldShop = shopService.findShopById(id);
        oldShop.setName(shop.getName());
        oldShop.setCertificate(shop.getCertificate());
        oldShop.setContactEmail(shop.getContactEmail());
        oldShop.setContactNumber(shop.getContactNumber());

        Shop updatedShop = shopService.save(shop);
        return JSONConvert.JSONConverter(updatedShop);
    }

    // Shop add product
    @PostMapping("/shop/{id}/product")
    public String addProduct (@PathVariable("id") Long id, @Valid @RequestBody Product product) {
        System.out.println("Add a product to a shop");
        System.out.println();
        System.out.println();
        Shop oldShop = shopService.findShopById(id);
        product.setId(nextId.incrementAndGet());
        System.out.println(product);
        oldShop.addProduct(new Product(product.getId(), product.getName(), product.getDescription(), product.getProductImg()));
        return  JSONConvert.JSONConverter(shopService.save(oldShop));
    }


    // Shop delete product
    @DeleteMapping("/shop/{shopId}/product/{productId}")
    public String delProduct (@PathVariable("shopId") Long shopId, @PathVariable("productId") Long productId) {
        Shop oldShop = shopService.findShopById(shopId);
        Product product = productService.findProductById(productId);
        oldShop.removeProduct(product);
        return JSONConvert.JSONConverter(shopService.save(oldShop));
    }
}
