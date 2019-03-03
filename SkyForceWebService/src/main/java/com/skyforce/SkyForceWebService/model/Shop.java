package com.skyforce.SkyForceWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Shop")
public class Shop {

    @Id
    @Column(name="ID", unique = true)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="CONTACT_NUMBER", nullable = false)
    private String contactNumber;

    @Column(name="CONTACT_EMAIL", nullable = false)
    private String contactEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDOR_ID", nullable = false)
    private Vendor vendor;

    public Shop(Long id, String name, String contactNumber, String contactEmail, Vendor vendor) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.vendor = vendor;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
