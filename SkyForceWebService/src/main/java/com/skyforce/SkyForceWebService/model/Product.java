package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Entity
@Table(name="Product")
public class Product {

    @Id
    @Column(name="ID", unique = true)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="DESCRIPTION", nullable = false)
    private String description;

    @ElementCollection
    private List<URL> productImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOP_ID")
    @JsonBackReference
    private Shop shop;

    public Product() {
    }

    public Product(Long id, String name, String description, List<URL> productImg) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productImg = productImg;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productImg=" + productImg +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<URL> getProductImg() {
        return productImg;
    }

    public void setProductImg(List<URL> productImg) {
        this.productImg = productImg;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
