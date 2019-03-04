package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vendor")
public class Vendor extends User {
    // Vendor

    // orphanRemoval = true in One to Many? How it works?


    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<Shop> shops = new ArrayList<Shop>();

    public Vendor(Long id, String firstName, String lastName, GenderEnm gender, String email, ArrayList<Shop> shops) {
        super(id, firstName, lastName, gender, email);
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
}
