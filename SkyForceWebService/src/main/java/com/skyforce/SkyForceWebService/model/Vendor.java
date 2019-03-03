package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vendor")
public class Vendor extends User {
    // Vendor

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
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
                "shops=" + shops +
                '}';
    }


}
