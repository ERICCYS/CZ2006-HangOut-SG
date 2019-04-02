package com.skyforce.SkyForceWebService.repo;


import com.skyforce.SkyForceWebService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    Product findById(Long id);

    Product save(Product product);

    void delete(Product product);
}
