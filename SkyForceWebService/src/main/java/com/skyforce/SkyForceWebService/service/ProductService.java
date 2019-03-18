package com.skyforce.SkyForceWebService.service;

import com.skyforce.SkyForceWebService.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findProductById(Long id);

    Product save(Product product);

    void delete(Product product);
}
