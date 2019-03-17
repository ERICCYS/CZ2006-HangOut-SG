package com.skyforce.SkyForceWebService.repo;


import com.skyforce.SkyForceWebService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    // find a product by id
    Product findById(Long id);

    // insert one product
    Product save(Product product);

    // delete one product
    void delete(Product product);
}
