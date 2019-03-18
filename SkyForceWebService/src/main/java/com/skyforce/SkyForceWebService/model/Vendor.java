package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vendor")
public class Vendor extends User {
    // Vendor

    // orphanRemoval = true in One to Many? How it works?

    // TODO: Add shop method


    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Shop> shops = new ArrayList<>();


    public Vendor(Long id, String firstName, String lastName, GenderEnm gender, String email, String password, List<Shop> shops) {
        super(id, firstName, lastName, gender, email, password);
        this.shops = shops;
    }

    public Vendor() {
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + super.getId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", gender=" + super.getGender() +
                ", email='" + super.getEmail() + '\'' +
                ", shops=" + shops +
                '}';
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public void addShop(Shop shop) {
        shops.add(shop);
        shop.setVendor(this);
    }

    public void removeShop(Shop shop) {
        shops.remove(shop);
        shop.setVendor(null);
    }
}
