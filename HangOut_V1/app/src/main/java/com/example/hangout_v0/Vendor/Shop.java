package com.example.hangout_v0.Vendor;


import java.net.URL;
import java.util.ArrayList;

public class Shop {

    private String name;

    private Long shopId;
    private String contactNumber;


    public Shop() {
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Shop(String name, String contactNumber, Long shopId) {
        this.name = name;
        this.shopId = shopId;
        this.contactNumber = contactNumber;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


}
