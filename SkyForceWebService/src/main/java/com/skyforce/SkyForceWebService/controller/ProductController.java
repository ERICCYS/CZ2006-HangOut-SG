package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.config.JSONConvert;
import com.skyforce.SkyForceWebService.model.Product;
import com.skyforce.SkyForceWebService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String getAllProducts() {
        List<Product> products = productService.findAll();
        return JSONConvert.JSONConverter(products);
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        return JSONConvert.JSONConverter(product);
    }

}

