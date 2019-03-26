package com.skyforce.SkyForceWebService.model;


import java.net.URL;
import java.util.ArrayList;

public class Shop {

    private String name;


    private String contactNumber;


    public Shop() {
    }

    public Shop(String name, String contactNumber) {
        this.name = name;
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
