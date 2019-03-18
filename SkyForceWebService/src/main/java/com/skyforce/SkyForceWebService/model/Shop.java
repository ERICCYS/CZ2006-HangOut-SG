package com.skyforce.SkyForceWebService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name="VERIFIED", nullable = false)
    private boolean verified;

    @Column(name="CERTIFICATE")
    private URL certificate;


    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VENDOR_ID")
    @JsonBackReference
    private Vendor vendor;

    public Shop() {
    }

    public Shop(Long id, String name, String contactNumber, String contactEmail) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.verified = false;
        this.certificate = null;
    }

    public Shop(Long id, String name, String contactNumber, String contactEmail, List<Product> products) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.verified = false;
        this.certificate = null;
        this.products = products;
    }

    public Shop(Long id, String name, String contactNumber, String contactEmail, URL certificate) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.verified = false;
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", verified=" + verified +
                ", certificate=" + certificate +
                ", products=" + products +
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public URL getCertificate() {
        return certificate;
    }

    public void setCertificate(URL certificate) {
        this.certificate = certificate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setShop(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setShop(null);
    }

}
